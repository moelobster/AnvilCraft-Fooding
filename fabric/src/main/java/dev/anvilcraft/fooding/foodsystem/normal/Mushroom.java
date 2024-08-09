package dev.anvilcraft.fooding.foodsystem.normal;

import dev.dubhe.anvilcraft.init.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Mushroom implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        if(Math.random()<0.1){
            double a = Math.random();
            ItemStack goldIngotStack;
            if(a<0.5){
                goldIngotStack = new ItemStack(Items.RED_MUSHROOM);
            }else {
                goldIngotStack = new ItemStack(Items.BROWN_MUSHROOM);
            }
            ItemEntity goldIngotEntity = new ItemEntity(player.level(), player.xOld, player.yOld, player.zOld, goldIngotStack);
            player.level().addFreshEntity(goldIngotEntity);
        }
    }
}
