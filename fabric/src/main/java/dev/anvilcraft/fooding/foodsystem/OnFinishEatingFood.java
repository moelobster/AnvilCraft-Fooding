package dev.anvilcraft.fooding.foodsystem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Set;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.MOD_ID;
import static dev.anvilcraft.fooding.foodsystem.haste.Hate.HATE1;
import static dev.anvilcraft.fooding.foodsystem.like.Like.*;
import static dev.anvilcraft.fooding.foodsystem.FoodEfeects.*;

public class OnFinishEatingFood {
    public static void onFinishEating(ItemStack itemStack, Level level, LivingEntity livingEntity){
        if (itemStack.isEdible()&&livingEntity instanceof ServerPlayer player){
            CompoundTag playerFoodsDatas =((FoodsData)player).getFoodsData();
            CompoundTag playerFoodsData = (CompoundTag) playerFoodsDatas.get(MOD_ID);
            CompoundTag itemFoodsData = itemStack.getTagElement(MOD_ID);
            if (itemFoodsData != null) {
                Set<String> keys = itemFoodsData.getAllKeys();
                for(String key:keys){
                    if(playerFoodsData==null || !playerFoodsData.contains(key)){
                        CompoundTag tag = new CompoundTag();
                        tag.putInt(NONE.get(), 1);
                        if(playerFoodsData == null){
                            playerFoodsData = new CompoundTag();
                        }
                        playerFoodsData.put(key,tag);
                    }else{
                        Set<String> k =((CompoundTag) playerFoodsData.get(key)).getAllKeys();
                        for(String ck:k){
                            CompoundTag tag = new CompoundTag();
                            int grade = ((CompoundTag) playerFoodsData.get(key)).getInt(ck) + 1 ;
                            if(grade == 4){
                                int result = (Math.random() < 0.5) ? 1 : 2;
                                if(result == 1){
                                    tag.putInt(NONE.random(), grade);
                                }else{
                                    tag.putInt(HATE1.random(), grade);
                                }
                            }else{
                                tag.putInt(ck,grade);
                                if(grade > 4){
                                    foodEffects(player,key,tag,itemFoodsData.getInt(key));
                                }
                            }
                            playerFoodsData.remove(key);
                            playerFoodsData.put(key,tag);
                        }
                    }
                }
                CompoundTag outcome = new CompoundTag();
                outcome.put(MOD_ID,playerFoodsData);
                ((FoodsData)player).setFoodsData(outcome);
            }
        }
    }
}
