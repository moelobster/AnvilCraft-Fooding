package dev.anvilcraft.fooding.event;

import com.google.common.base.Predicates;
import dev.anvilcraft.fooding.AnvilCraftFooding;
import dev.anvilcraft.fooding.foodsystem.FoodsData;
import dev.anvilcraft.lib.event.SubscribeEvent;
import dev.dubhe.anvilcraft.api.event.entity.AnvilFallOnLandEvent;
import dev.dubhe.anvilcraft.api.event.entity.AnvilHurtEntityEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.MOD_ID;

public class FoodCraftEventListener {
    @SubscribeEvent
    public void diedPlayer(@NotNull AnvilHurtEntityEvent event){
        Entity hurtedEntity = event.getHurtedEntity();
        if(!(hurtedEntity instanceof ServerPlayer player))  return;
        if (!(hurtedEntity.level() instanceof ServerLevel serverLevel)) return;
        if(player.isAlive())   return;
        CompoundTag playerTag = ((FoodsData)player).getFoodsData();
        CompoundTag compoundTag = new CompoundTag();
    }
    @SubscribeEvent
    public void onLand(@NotNull AnvilFallOnLandEvent event){
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        MinecraftServer server = level.getServer();
        if (null == server) return;
        BlockPos belowPos = pos.below();
        BlockState state = level.getBlockState(belowPos);
        if(state.is(Blocks.SCAFFOLDING)){
            belowPos = belowPos.below();
            state = level.getBlockState(belowPos);
            if(state.is(Blocks.IRON_TRAPDOOR)){
                addSeasoning(level,pos);
            }
        }
    }

    public static void addSeasoning(Level level,BlockPos pos){
        AABB aabb1 = new AABB(pos).move(0,-1,0);
        List<ItemEntity> entities1 =
                level.getEntities(EntityTypeTest.forClass(ItemEntity.class), aabb1, Predicates.alwaysTrue());
        AABB aabb2 = new AABB(pos).move(0,-2,0);
        List<ItemEntity> entities2 =
                level.getEntities(EntityTypeTest.forClass(ItemEntity.class), aabb2, Predicates.alwaysTrue());
        if(entities2.size() != 1 || entities1.size() != 1){
            return;
        }else{
            ItemEntity itemEntity1 = entities1.get(0);
            ItemEntity itemEntity2 = entities2.get(0);
            ItemStack itemStack1 = itemEntity1.getItem();
            ItemStack itemStack2 = itemEntity2.getItem();
            CompoundTag compoundTag1 = itemStack1.getTagElement(MOD_ID);
            CompoundTag compoundTag2 = itemStack2.getTagElement(MOD_ID);
            if (compoundTag1 != null) {
                Set<String> keys = compoundTag1.getAllKeys();
                for(String key:keys){
                    if(compoundTag2.contains(key)){
                        int grade = compoundTag1.getInt(key)+compoundTag2.getInt(key);
                        compoundTag2.remove(key);
                        if(grade != 0){
                            compoundTag2.putInt(key,grade);
                        }
                    }else{
                        compoundTag2.putInt(key,compoundTag1.getInt(key));
                    }
                }
            }
            itemStack1.setCount(itemStack1.getCount()-1);
            itemEntity1.setItem(itemStack1);
            CompoundTag oucomeTag = new CompoundTag();
            if (compoundTag2 != null) {
                oucomeTag.put(MOD_ID,compoundTag2);
            }
            itemStack2.setTag(oucomeTag);
        }
    }
}
