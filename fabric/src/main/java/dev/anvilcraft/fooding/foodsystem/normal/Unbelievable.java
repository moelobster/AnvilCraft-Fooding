package dev.anvilcraft.fooding.foodsystem.normal;

import dev.dubhe.anvilcraft.init.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

public class Unbelievable implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        if(Math.random()<0.2){
            ItemStack goldIngotStack = new ItemStack(ModItems.ROYAL_STEEL_NUGGET);
            ItemEntity goldIngotEntity = new ItemEntity(player.level(), player.xOld, player.yOld, player.zOld, goldIngotStack);
            player.level().addFreshEntity(goldIngotEntity);
        }
    }
}
