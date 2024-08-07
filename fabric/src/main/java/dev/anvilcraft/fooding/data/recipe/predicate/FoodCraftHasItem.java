package dev.anvilcraft.fooding.data.recipe.predicate;

import com.google.common.base.Predicates;
import com.google.gson.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dubhe.anvilcraft.AnvilCraft;
import dev.dubhe.anvilcraft.data.recipe.anvil.AnvilCraftingContext;
import dev.dubhe.anvilcraft.data.recipe.anvil.HasData;
import dev.dubhe.anvilcraft.data.recipe.anvil.RecipePredicate;
import lombok.Getter;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.MOD_ID;

public class FoodCraftHasItem implements RecipePredicate, HasData {
    @Getter
    private final String type = "foodcraft_has_item";
    protected final Vec3 offset;
    @Getter
    protected final ModFoodPredicate matchItem;
    protected String path = null;
    @Getter
    protected Map.Entry<String, CompoundTag> data = null;
    protected final List<String> hasTag = new ArrayList<>();
    protected final List<String> notHasTag = new ArrayList<>();



    public FoodCraftHasItem(Vec3 offset, ModFoodPredicate matchItem) {
        this.offset = offset;
        this.matchItem = matchItem;
    }

    public FoodCraftHasItem saveItemData(String path) {
        this.path = path;
        return this;
    }

    public FoodCraftHasItem hasTag(String path) {
        this.hasTag.add(path);
        return this;
    }

    public FoodCraftHasItem notHasTag(String path) {
        this.notHasTag.add(path);
        return this;
    }

    /**
     * 拥有物品
     *
     * @param serializedRecipe 序列化配方
     */
    public FoodCraftHasItem(JsonObject serializedRecipe) {
        JsonArray array = GsonHelper.getAsJsonArray(serializedRecipe, "offset");
        double[] vec3 = {0.0d, 0.0d, 0.0d};
        for (int i = 0; i < array.size() && i < 3; i++) {
            JsonElement element = array.get(i);
            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
                vec3[i] = element.getAsDouble();
            } else throw new JsonSyntaxException("Expected offset to be a Double, was " + GsonHelper.getType(element));
        }
        this.offset = new Vec3(vec3[0], vec3[1], vec3[2]);
        if (serializedRecipe.has("data_path")) {
            this.path = GsonHelper.getAsString(serializedRecipe, "data_path");
        }
        if (!serializedRecipe.has("match_item")) throw new JsonSyntaxException("Missing match_item");
        if (serializedRecipe.has("has_tag")) {
            JsonArray array1 = GsonHelper.getAsJsonArray(serializedRecipe, "has_tag");
            array1.forEach(element -> this.hasTag.add(element.getAsString()));
        }
        if (serializedRecipe.has("not_has_tag")) {
            JsonArray array1 = GsonHelper.getAsJsonArray(serializedRecipe, "not_has_tag");
            array1.forEach(element -> this.notHasTag.add(element.getAsString()));
        }
        this.matchItem = ModFoodPredicate.fromJson(serializedRecipe.get("match_item"));
    }

    /**
     * @param buffer 缓冲区
     */
    public FoodCraftHasItem(@NotNull FriendlyByteBuf buffer) {
        this.offset = new Vec3(buffer.readVector3f());
        if (buffer.readBoolean()) {
            this.path = buffer.readUtf();
        }
        int read = buffer.readVarInt();
        for (int i = 0; i < read; i++) {
            this.hasTag.add(buffer.readUtf());
        }
        read = buffer.readVarInt();
        for (int i = 0; i < read; i++) {
            this.notHasTag.add(buffer.readUtf());
        }
        this.matchItem = ModFoodPredicate.fromJson(AnvilCraft.GSON.fromJson(buffer.readUtf(), JsonElement.class));
    }

    @Override
    public boolean matches(@NotNull AnvilCraftingContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getPos();
        AABB aabb = new AABB(pos).move(this.offset);
        List<ItemEntity> entities =
                level.getEntities(EntityTypeTest.forClass(ItemEntity.class), aabb, Predicates.alwaysTrue());
        entities:
        for (ItemEntity entity : entities) {
            ItemStack item = entity.getItem();
            if (this.matchItem.matches(item)) {
                for (String path : this.hasTag) {
                    if (!item.hasTag()) continue;
                    CompoundTag tag = item.getOrCreateTag();
                    String[] paths = path.split("\\.");
                    for (int i = 0; i < paths.length; i++) {
                        if (!tag.contains(paths[i])) continue entities;
                        if (i != paths.length - 1) {
                            tag = tag.getCompound(paths[i]);
                        }
                    }
                }
                for (String path : this.notHasTag) {
                    CompoundTag tag = item.getOrCreateTag();
                    String[] paths = path.split("\\.");
                    for (int i = 0; i < paths.length; i++) {
                        if (i != paths.length - 1) {
                            tag = tag.getCompound(paths[i]);
                        } else if (tag.contains(paths[i])) continue entities;
                    }
                }
                if (this.path != null) {
                    this.data = Map.entry(this.path, item.hasTag() ? item.getOrCreateTag() : new CompoundTag());
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean process(AnvilCraftingContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getPos();
        AABB aabb = new AABB(pos).move(this.offset);
        List<ItemEntity> entities =
                level.getEntities(EntityTypeTest.forClass(ItemEntity.class), aabb, Predicates.alwaysTrue());
        for (ItemEntity entity : entities) {
            ItemStack item = entity.getItem();
            if (this.matchItem.matches(item)) {
                if(item.getTagElement(MOD_ID) != null){
                    ItemStack itemStack =  item.getItem().getDefaultInstance();
                    CompoundTag compoundTag = cmp(item,itemStack);//变化的tag
                    CompoundTag compoundTag1 =  context.getData(MOD_ID,CompoundTag.class);//已经包含的变化的tag
                    CompoundTag predicate = new CompoundTag();
                    context.clearData();
                    if(compoundTag1 != null){
                        compoundTag1 = (CompoundTag) compoundTag1.get(MOD_ID);
                        Set<String> keys = compoundTag.getAllKeys();
                        if(compoundTag1 != null){
                            for(String key :keys){
                                if(compoundTag1.contains(key)){
                                    int grade = compoundTag.getInt(key) + compoundTag1.getInt(key);
                                    if(grade != 0){
                                        predicate.putInt(key,grade);
                                    }
                                }else{
                                    predicate.putInt(key,compoundTag.getInt(key));
                                }
                            }
                        }
                        keys.clear();
                        keys = compoundTag.getAllKeys();
                        for(String key :keys){
                            if(!compoundTag.contains(key)){
                                predicate.putInt(key,compoundTag.getInt(key));
                            }
                        }
                    }else{
                        Set<String> keys = compoundTag.getAllKeys();
                        for(String key:keys){
                            predicate.putInt(key,compoundTag.getInt(key));
                        }
                    }
                    context.addData(MOD_ID,predicate);
                }
                int count = this.matchItem.count.getMin() != null ? this.matchItem.count.getMin() : 1;
                if (item.getItem().hasCraftingRemainingItem()) {
                    assert item.getItem().getCraftingRemainingItem() != null;
                    ItemStack stack = new ItemStack(item.getItem().getCraftingRemainingItem(), count);
                    Vec3 vec3 = pos.getCenter().add(this.offset);
                    ItemEntity itemEntity = new ItemEntity(level, vec3.x, vec3.y, vec3.z, stack, 0.0, 0.0, 0.0);
                    itemEntity.setDefaultPickUpDelay();
                    level.addFreshEntity(itemEntity);
                }
                item.shrink(count);
                entity.setItem(item.copy());
                return true;
            }
        }
        return false;
    }

    public CompoundTag cmp(@NotNull ItemStack itemStack,@NotNull ItemStack itemStack2){
        CompoundTag compoundTag =itemStack.getTagElement(MOD_ID);
        CompoundTag compoundTag2 =itemStack2.getTagElement(MOD_ID);
        CompoundTag predicate = new CompoundTag();
        Set<String> keys = compoundTag.getAllKeys();
        for ( String key : keys){
            if(compoundTag2.contains(key)){
                int grade = compoundTag.getInt(key) - compoundTag2.getInt(key);
                if(grade != 0){
                    predicate.putInt(key,grade);
                }
            }else{
                predicate.putInt(key,compoundTag.getInt(key));
            }
        }
        Set<String> keys2 = compoundTag2.getAllKeys();
        for(String key : keys2){
            if(!compoundTag.contains(key)){
                int grade = -compoundTag2.getInt(key);
                predicate.putInt(key,grade);
            }
        }
        return predicate;
    }

    @Override
    public void toNetwork(@NotNull FriendlyByteBuf buffer) {
        buffer.writeUtf(this.getType());
        buffer.writeVector3f(this.offset.toVector3f());
        buffer.writeBoolean(this.path != null);
        if (this.path != null) {
            buffer.writeUtf(this.path);
        }
        buffer.writeVarInt(this.hasTag.size());
        this.hasTag.forEach(buffer::writeUtf);
        buffer.writeVarInt(this.notHasTag.size());
        this.notHasTag.forEach(buffer::writeUtf);
        buffer.writeUtf(this.matchItem.serializeToJson().toString());
    }

    @Override
    public @NotNull JsonElement toJson() {
        double[] vec3 = {this.offset.x(), this.offset.y(), this.offset.z()};
        JsonArray offset = new JsonArray();
        for (double v : vec3) {
            offset.add(new JsonPrimitive(v));
        }
        JsonObject object = new JsonObject();
        object.addProperty("type", this.getType());
        object.add("offset", offset);
        if (this.path != null) object.addProperty("data_path", this.path);
        JsonElement matchItem = this.matchItem.serializeToJson();
        object.add("match_item", matchItem);
        JsonArray hasTag = new JsonArray();
        this.hasTag.forEach(hasTag::add);
        if (!hasTag.isEmpty()) object.add("has_tag", hasTag);
        JsonArray notHasTag = new JsonArray();
        this.notHasTag.forEach(notHasTag::add);
        if (!notHasTag.isEmpty()) object.add("not_has_tag", notHasTag);
        return object;
    }

    @Getter
    @SuppressWarnings({"UnusedReturnValue", "unused"})
    public static class ModFoodPredicate {
        @Nullable
        private TagKey<Item> tag = null;
        private final Set<Item> items = new HashSet<>();
        @Nullable
        private CompoundTag nbt = null;
        @Getter
        public MinMaxBounds.Ints count = null;
        private MinMaxBounds.Ints durability = null;
        private final Map<Enchantment, MinMaxBounds.Ints> enchantments = new HashMap<>();

        private ModFoodPredicate() {
        }

        /**
         * 是否具有相同的物品/标签
         */
        @SuppressWarnings("BooleanMethodIsAlwaysInverted")
        public boolean sameItemsOrTag(ModFoodPredicate predicate) {
            if (this == predicate) return true;
            if (this.tag == null && predicate.tag != null) return false;
            if (this.tag != null && predicate.tag == null) return false;
            if (this.tag != null && !this.tag.location().equals(predicate.tag.location())) {
                return false;
            }
            if (this.items.size() != predicate.items.size()) return false;
            for (Item item : items) {
                if (!predicate.items.contains(item)) return false;
            }
            return true;
        }

        /**
         * ModItemPredicate
         */
        public static @NotNull ModFoodPredicate of() {
            return new ModFoodPredicate();
        }

        /**
         * ModItemPredicate
         */
        public static @NotNull ModFoodPredicate of(ItemLike @NotNull ... items) {
            ModFoodPredicate predicate = new ModFoodPredicate();
            for (ItemLike item : items) {
                predicate.items.add(item.asItem());
            }
            return predicate;
        }

        /**
         * ModItemPredicate
         */
        public static @NotNull ModFoodPredicate of(TagKey<Item> tag) {
            ModFoodPredicate predicate = new ModFoodPredicate();
            predicate.tag = tag;
            return predicate;
        }

        /**
         * ModItemPredicate
         */
        public @NotNull ModFoodPredicate with(ItemLike @NotNull ... items) {
            for (ItemLike item : items) {
                this.items.add(item.asItem());
            }
            return this;
        }

        /**
         * ModItemPredicate
         */
        public @NotNull ModFoodPredicate withTag(TagKey<Item> tag) {
            this.tag = tag;
            return this;
        }

        /**
         * ModItemPredicate
         */
        public ModFoodPredicate add(ItemLike @NotNull ... items) {
            for (ItemLike item : items) {
                this.items.add(item.asItem());
            }
            return this;
        }

        /**
         * ModItemPredicate
         */
        public ModFoodPredicate withCount(MinMaxBounds.Ints count) {
            this.count = count;
            return this;
        }

        /**
         * ModItemPredicate
         */
        public ModFoodPredicate withDurability(MinMaxBounds.Ints durability) {
            this.durability = durability;
            return this;
        }

        /**
         * ModItemPredicate
         */
        public ModFoodPredicate withNbt(String key, Tag value) {
            if (this.nbt == null) this.nbt = new CompoundTag();
            this.nbt.put(key, value);
            return this;
        }

        /**
         * ModItemPredicate
         */
        public ModFoodPredicate withNbt(CompoundTag tag) {
            if (this.nbt == null) this.nbt = tag.copy();
            else this.nbt.merge(tag);
            return this;
        }

        /**
         * ModItemPredicate
         */
        public ModFoodPredicate withEnchantments(MinMaxBounds.Ints levels, Enchantment @NotNull ... enchantments) {
            for (Enchantment enchantment : enchantments) {
                this.enchantments.put(enchantment, levels);
            }
            return this;
        }

        /**
         * ModItemPredicate
         */
        public static @NotNull ModFoodPredicate fromJson(JsonElement matchItem) {
            JsonObject object = GsonHelper.convertToJsonObject(matchItem, "match_item");
            ModFoodPredicate predicate = ModFoodPredicate.of();
            if (object.has("count")) {
                predicate.withCount(MinMaxBounds.Ints.fromJson(GsonHelper.getNonNull(object, "count")));
            }
            if (object.has("durability")) {
                predicate.withDurability(MinMaxBounds.Ints.fromJson(GsonHelper.getNonNull(object, "durability")));
            }
            if (object.has("tag")) {
                predicate.withTag(
                        TagKey.create(Registries.ITEM, new ResourceLocation(GsonHelper.getAsString(object, "tag")))
                );
            }
            if (object.has("enchantments")) {
                JsonObject enchants = GsonHelper.getAsJsonObject(object, "enchantments");
                for (Map.Entry<String, JsonElement> entry : enchants.entrySet()) {
                    Enchantment enchantment = BuiltInRegistries.ENCHANTMENT.get(new ResourceLocation(entry.getKey()));
                    if (enchantment == null) continue;
                    MinMaxBounds.Ints levels = MinMaxBounds.Ints.fromJson(entry.getValue());
                    predicate.withEnchantments(levels, enchantment);
                }
            }
            if (object.has("items")) {
                JsonArray array = GsonHelper.getAsJsonArray(object, "items");
                for (JsonElement element : array) {
                    String id = GsonHelper.convertToString(element, "item");
                    Item item = BuiltInRegistries.ITEM.get(new ResourceLocation(id));
                    predicate.with(item);
                }
            }
            if (object.has("nbt")) {
                try {
                    CompoundTag tag1 = TagParser.parseTag(GsonHelper.getAsString(object, "nbt"));
                    predicate.withNbt(tag1);
                } catch (CommandSyntaxException e) {
                    AnvilCraft.LOGGER.error(e.getMessage(), e);
                }
            }
            return predicate;
        }

        /**
         * ModItemPredicate
         */
        public JsonElement serializeToJson() {
            JsonObject object = new JsonObject();
            if (this.count != null) object.add("count", this.count.serializeToJson());
            if (this.durability != null) object.add("durability", this.durability.serializeToJson());
            if (this.tag != null) object.addProperty("tag", this.tag.location().toString());
            if (this.nbt != null) object.addProperty("nbt", this.nbt.toString());
            if (!this.items.isEmpty()) {
                JsonArray items = new JsonArray();
                for (Item item : this.items) {
                    items.add(BuiltInRegistries.ITEM.getKey(item).toString());
                }
                object.add("items", items);
            }
            if (!enchantments.isEmpty()) {
                JsonObject enchantments = new JsonObject();
                for (Map.Entry<Enchantment, MinMaxBounds.Ints> entry : this.enchantments.entrySet()) {
                    ResourceLocation location = BuiltInRegistries.ENCHANTMENT.getKey(entry.getKey());
                    if (location == null) continue;
                    enchantments.add(location.toString(), entry.getValue().serializeToJson());
                }
                object.add("enchantments", enchantments);
            }
            return object;
        }

        /**
         * ModItemPredicate
         */
        public boolean matches(ItemStack item) {
            boolean isItem = (this.tag != null && item.is(this.tag)) || this.items.stream().anyMatch(item::is);

            return isItem
                    && (this.count == null || this.count.matches(item.getCount()))
                    && (this.durability == null || this.durability.matches(item.getDamageValue()))
                    ;
        }
    }
}
