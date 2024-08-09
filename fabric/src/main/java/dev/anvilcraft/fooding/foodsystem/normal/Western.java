package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Western implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        if(Math.random()<0.05){
            ItemStack goldIngotStack = new ItemStack(Items.REDSTONE);
            ItemEntity goldIngotEntity = new ItemEntity(player.level(), player.xOld, player.yOld, player.zOld, goldIngotStack);
            player.level().addFreshEntity(goldIngotEntity);
        }
    }
}
