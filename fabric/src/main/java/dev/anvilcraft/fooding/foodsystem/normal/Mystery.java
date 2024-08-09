package dev.anvilcraft.fooding.foodsystem.normal;

import dev.dubhe.anvilcraft.init.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Mystery implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        if(Math.random()<0.05){
            ItemStack goldIngotStack = new ItemStack(ModItems.NETHERITE_CRYSTAL_NUCLEUS);
            ItemEntity goldIngotEntity = new ItemEntity(player.level(), player.xOld, player.yOld, player.zOld, goldIngotStack);
            player.level().addFreshEntity(goldIngotEntity);
        }
    }
}
