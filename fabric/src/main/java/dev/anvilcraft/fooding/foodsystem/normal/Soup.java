package dev.anvilcraft.fooding.foodsystem.normal;

import dev.dubhe.anvilcraft.init.ModItems;
import dev.latvian.mods.kubejs.level.gen.filter.mob.MobFilter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

public class Soup implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        if(player.hasEffect(MobEffects.POISON)){
            player.removeEffect(MobEffects.POISON);
        }
    }
}
