package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class Sweet implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,60,0));
    }
}
