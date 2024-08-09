package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class Bitter implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,100,0));
    }
}
