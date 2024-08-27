package dev.anvilcraft.fooding.event;

import com.google.common.base.Predicates;
import dev.anvilcraft.fooding.block.entity.FoodAnalyzerBlockEntity;
import dev.anvilcraft.fooding.foodsystem.FoodsData;
import dev.anvilcraft.fooding.foodsystem.hate.Hate;
import dev.anvilcraft.fooding.foodsystem.like.Like;
import dev.anvilcraft.fooding.init.ModBlocks;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.MOD_ID;

public class FoodCraftEventListener {
    @SubscribeEvent
    public void diedPlayer(@NotNull AnvilHurtEntityEvent event){
        Entity hurtedEntity = event.getHurtedEntity();
        if(!(hurtedEntity instanceof ServerPlayer player))  return;
        if (!(hurtedEntity.level() instanceof ServerLevel serverLevel)) return;
        if(player.isAlive())   return;
        CompoundTag compoundTag = ((FoodsData)player).getFoodsData();
        CompoundTag playerTag = compoundTag.getCompound(MOD_ID);
        List<String> hateList = new ArrayList<>();
        List<String> likeList = new ArrayList<>();
        Set<String> tasteList = playerTag.getAllKeys();
        for( String taste : tasteList ){
            CompoundTag attitude = playerTag.getCompound(taste);
            for(Hate hate : Hate.values()){
                if(attitude.contains(hate.get())){
                    hateList.add(taste);
                    break;
                }
            }
            for (Like like : Like.values()){
                if(attitude.contains(like.get())){
                    likeList.add(taste);
                    break;
                }
            }
        }
        if(!hateList.isEmpty()){
            Random random = new Random();
            int tmp = random.nextInt(hateList.size());
            playerTag.remove(hateList.get(tmp));
        }
        if(!likeList.isEmpty()){
            Random random = new Random();
            int tmp = random.nextInt(likeList.size());
            playerTag.remove(likeList.get(tmp));
        }
        CompoundTag outcome = new CompoundTag();
        outcome.put(MOD_ID,playerTag);
        ((FoodsData) player).setFoodsData(outcome);
    }
    @SubscribeEvent
    public void onLand(@NotNull AnvilFallOnLandEvent event){
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        MinecraftServer server = level.getServer();
        if (null == server) return;
        BlockPos belowPos = pos.below();
        BlockState state = level.getBlockState(belowPos);
        if(state.is(ModBlocks.FOOD_ANALYZER.get())){
            FoodAnalyzerBlockEntity entity = (FoodAnalyzerBlockEntity) level.getBlockEntity(belowPos);
            entity.craft(level);
        }
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
        }else{
            ItemEntity itemEntity1 = entities1.get(0);
            ItemEntity itemEntity2 = entities2.get(0);
            ItemStack itemStack1 = itemEntity1.getItem();
            ItemStack itemStack2 = itemEntity2.getItem();
            CompoundTag Tag1 = itemStack1.getTagElement(MOD_ID);
            CompoundTag Tag2 = itemStack2.getTagElement(MOD_ID);
            if(Tag1 == null || Tag2 == null)   return;
            CompoundTag compoundTag1 = Tag1.copy();
            CompoundTag compoundTag2 = Tag2.copy();
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
            itemStack1.setCount(itemStack1.getCount()-1);
            itemEntity1.setItem(itemStack1);
            CompoundTag oucomeTag = new CompoundTag();
            oucomeTag.put(MOD_ID,compoundTag2);
            ItemStack itemStack3 = itemStack2.copy();
            itemStack3.setTag(oucomeTag);
            itemStack3.setCount(1);
            itemStack2.setCount(itemStack2.getCount()-1);
            itemEntity2.setItem(itemStack2);
            ItemEntity itemEntity3 = new ItemEntity(level,pos.getX()+0.5,pos.getY()-1.5, pos.getZ()+0.5,itemStack3);
            itemEntity3.setDeltaMovement(0,0,0);
            level.addFreshEntity(itemEntity3);
        }
    }
}
