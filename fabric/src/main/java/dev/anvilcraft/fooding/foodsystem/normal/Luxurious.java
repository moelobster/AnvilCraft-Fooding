package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

public class Luxurious implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        if(Math.random()<0.5){
            ItemStack goldIngotStack = new ItemStack(Items.GOLD_NUGGET);
            ItemEntity goldIngotEntity = new ItemEntity(player.level(), player.xOld, player.yOld, player.zOld, goldIngotStack);
            player.level().addFreshEntity(goldIngotEntity);
        }
    }
}
