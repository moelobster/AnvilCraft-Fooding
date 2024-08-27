package dev.anvilcraft.fooding.item.minecraft;

import dev.dubhe.anvilcraft.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;

import static dev.anvilcraft.fooding.foodsystem.taste.Tastes.*;
import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.MOD_ID;

public class FoodFix {
    public static Map<Item, CompoundTag> FoodList = new HashMap<>();
    public static void register(){
        register(Items.APPLE, new FoodTag()
                .add(FRUIT.get(),1));
        register(Items.GOLDEN_APPLE,new FoodTag()
                .add(LUXURIOUS.get(), 1)
                .add(FRUIT.get(), 1));
        register(Items.ENCHANTED_GOLDEN_APPLE,new FoodTag()
                .add(LUXURIOUS.get(),2)
                .add(FRUIT.get(),1)
                .add(MYSTERY.get(), 1)
                .add(UNBELIEVABLE.get(), 1));
        register(Items.MELON_SLICE,new FoodTag()
                .add(FRUIT.get(),2));
        register(Items.SWEET_BERRIES,new FoodTag()
                .add(FRUIT.get(),1));
        register(Items.GLOW_BERRIES,new FoodTag()
                .add(FRUIT.get(),1)
                .add(UNBELIEVABLE.get(),1));
        register(Items.CHORUS_FRUIT,new FoodTag()
                .add(FRUIT.get(),1)
                .add(UNBELIEVABLE.get(),1));
        register(Items.CARROT,new FoodTag()
                .add(VEGETABLE.get(), 1));
        register(Items.GOLDEN_CARROT, new FoodTag()
                .add(VEGETABLE.get(),1)
                .add(LUXURIOUS.get(),1));
        register(Items.POTATO,new FoodTag()
                .add(RAW.get(), 1)
                .add(VEGETABLE.get(), 1));
        register(Items.BAKED_POTATO,new FoodTag()
                .add(VEGETABLE.get(), 1)
                .add(FULL.get(), 1)
                .add(BAKED.get(), 1));
        register(Items.POISONOUS_POTATO,new FoodTag()
                .add(RAW.get(), 1)
                .add(VEGETABLE.get(), 1)
                .add(POISON.get(), 1));
        register(Items.BEETROOT,new FoodTag()
                .add(SWEET.get(), 1)
                .add(VEGETABLE.get(), 1));
        register(Items.DRIED_KELP,new FoodTag()
                .add(VEGETABLE.get(), 1)
                .add(SEAFOOD.get(), 1));
        register(Items.BEEF,new FoodTag()
                .add(MEAT.get(), 1)
                .add(RAW.get(), 1));
        register(Items.COOKED_BEEF,new FoodTag()
                .add(BAKED.get(), 1)
                .add(MEAT.get(), 1));
        register(Items.PORKCHOP,new FoodTag()
                .add(MEAT.get(), 1)
                .add(RAW.get(), 1));
        register(Items.COOKED_PORKCHOP,new FoodTag()
                .add(MEAT.get(), 1)
                .add(BAKED.get(), 1));
        register(Items.MUTTON,new FoodTag()
                .add(MEAT.get(), 1)
                .add(RAW.get(), 1));
        register(Items.COOKED_MUTTON,new FoodTag()
                .add(BAKED.get(), 1)
                .add(MEAT.get(), 1));
        register(Items.CHICKEN,new FoodTag()
                .add(POISON.get(), 1)
                .add(MEAT.get(), 1)
                .add(RAW.get(), 1));
        register(Items.COOKED_CHICKEN,new FoodTag()
                .add(MEAT.get(), 1)
                .add(BAKED.get(), 1));
        register(Items.RABBIT,new FoodTag()
                .add(MEAT.get(), 1)
                .add(RAW.get(), 1));
        register(Items.COOKED_RABBIT,new FoodTag()
                .add(MEAT.get(), 1)
                .add(BAKED.get(), 1));
        register(Items.COD,new FoodTag()
                .add(SEAFOOD.get(), 1)
                .add(MEAT.get(), 1)
                .add(RAW.get(), 1));
        register(Items.COOKED_COD,new FoodTag()
                .add(SEAFOOD.get(), 1)
                .add(MEAT.get(), 1)
                .add(BAKED.get(), 1));
        register(Items.SALMON,new FoodTag()
                .add(SEAFOOD.get(), 1)
                .add(MEAT.get(), 1)
                .add(RAW.get(), 1));
        register(Items.COOKED_SALMON,new FoodTag()
                .add(SEAFOOD.get(), 1)
                .add(MEAT.get(), 1)
                .add(BAKED.get(), 1));
        register(Items.TROPICAL_FISH,new FoodTag()
                .add(SEAFOOD.get(), 1)
                .add(MEAT.get(), 1)
                .add(RAW.get(), 1));
        register(Items.PUFFERFISH,new FoodTag()
                .add(SEAFOOD.get(), 1)
                .add(MEAT.get(), 1)
                .add(RAW.get(), 1)
                .add(POISON.get(), 1));
        register(Items.BREAD,new FoodTag()
                .add(FULL.get(), 1));
        register(Items.COOKIE,new FoodTag()
                .add(CHOCOLATE.get(), 1)
                .add(SWEET.get(), 1)
                .add(BAKED.get(), 1));
        register(Items.PUMPKIN_PIE,new FoodTag()
                .add(VEGETABLE.get(), 1)
                .add(FULL.get(), 1));
        register(Items.ROTTEN_FLESH,new FoodTag()
                .add(POISON.get(), 2));
        register(Items.SPIDER_EYE,new FoodTag()
                .add(POISON.get(), 2)
                .add(MYSTERY.get(), 1));
        register(Items.MUSHROOM_STEW,new FoodTag()
                .add(SOUP.get(), 1)
                .add(MUSHROOM.get(), 1)
                .add(WARM.get(), 1));
        register(Items.BEETROOT_SOUP,new FoodTag()
                .add(SOUP.get(), 1)
                .add(VEGETABLE.get(), 1)
                .add(WARM.get(), 1)
                .add(SWEET.get(), 1));
        register(Items.RABBIT_STEW,new FoodTag()
                .add(VEGETABLE.get(), 1)
                .add(SOUP.get(), 1)
                .add(MUSHROOM.get(), 1)
                .add(MEAT.get(), 1)
                .add(WARM.get(), 1));
        register(Items.MILK_BUCKET,new FoodTag()
                .add(MILK.get(), 1));
        register(ModItems.CREAM.asItem(),new FoodTag()
                .add(CREAM.get(), 1)
                .add(WESTERN.get(), 1));
        register(ModItems.FLOUR.asItem(), new FoodTag()
                .add(FULL.get(), 1));
        register(ModItems.DOUGH.asItem(), new FoodTag()
                .add(FULL.get(), 2));
        register(ModItems.COCOA_LIQUOR.asItem(), new FoodTag()
                .add(WESTERN.get(), 1)
                .add(CHOCOLATE.get(), 1));
        register(ModItems.COCOA_BUTTER.asItem(), new FoodTag()
                .add(WESTERN.get(), 1)
                .add(CHOCOLATE.get(), 1));
        register(ModItems.COCOA_POWDER.asItem(), new FoodTag()
                .add(WESTERN.get(), 1)
                .add(CHOCOLATE.get(), 1));
        register(ModItems.BEEF_MUSHROOM_STEW_RAW.asItem(), new FoodTag()
                .add(MEAT.get(), 1)
                .add(MUSHROOM.get(), 1)
                .add(RAW.get(), 1));
        register(ModItems.UTUSAN_RAW.asItem(), new FoodTag()
                .add(POISON.get(), 2)
                .add(MYSTERY.get(), 1)
                .add(RAW.get(), 1));
        register(ModItems.CHOCOLATE.asItem(), new FoodTag()
                .add(SWEET.get(), 2)
                .add(WESTERN.get(), 2)
                .add(CHOCOLATE.get(), 2));
        register(ModItems.CHOCOLATE_BLACK.asItem(), new FoodTag()
                .add(BITTER.get(), 1)
                .add(SWEET.get(), 1)
                .add(WESTERN.get(), 2)
                .add(CHOCOLATE.get(), 2));
        register(ModItems.CHOCOLATE_WHITE.asItem(), new FoodTag()
                .add(SWEET.get(), 3)
                .add(WESTERN.get(), 2)
                .add(CHOCOLATE.get(), 2));
        register(ModItems.CREAMY_BREAD_ROLL.asItem(), new FoodTag()
                .add(CREAM.get(), 1)
                .add(WESTERN.get(), 1)
                .add(FULL.get(), 2)
                .add(SWEET.get(), 1));
        register(ModItems.BEEF_MUSHROOM_STEW.asItem(), new FoodTag()
                .add(MEAT.get(), 1)
                .add(MUSHROOM.get(), 2)
                .add(SOUP.get(), 1)
                .add(WARM.get(), 1));
        register(ModItems.UTUSAN.asItem(), new FoodTag()
                .add(POISON.get(), 1)
                .add(MYSTERY.get(), 2));
        register(Items.HONEY_BOTTLE,new FoodTag()
                .add(SWEET.get(), 3));
        register(Items.KELP,new FoodTag()
                .add(SEAFOOD.get(), 1)
                .add(RAW.get(), 1));
        register(Items.SEA_PICKLE,new FoodTag()
                .add(SEAFOOD.get(), 1)
                .add(RAW.get(), 1));
        register(Items.SUGAR_CANE,new FoodTag()
                .add(SWEET.get(), 1)
                .add(VEGETABLE.get(), 1));
        register(Items.SUGAR,new FoodTag()
                .add(SWEET.get(), 2));
        register(Items.COCOA_BEANS,new FoodTag()
                .add(CHOCOLATE.get(), 1));
        register(Items.PUMPKIN,new FoodTag()
                .add(VEGETABLE.get(), 1));
        register(Items.MELON,new FoodTag()
                .add(FRUIT.get(), 1)
                .add(SWEET.get(), 1));
        register(Items.HONEY_BLOCK,new FoodTag()
                .add(SWEET.get(), 14));

    }
    public static void register(Item item, FoodTag foodTag){
        CompoundTag compoundTag1 = new CompoundTag();
        compoundTag1.put(MOD_ID,foodTag);
        FoodList.put(item,compoundTag1);
    }

    public static class FoodTag extends CompoundTag{
        public FoodTag add(String key, int value) {
            this.putInt(key, value);
            return this;
        }
    }

}
