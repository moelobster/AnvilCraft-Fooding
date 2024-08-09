package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class Seafood implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        player.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER,100,0));
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,100,0));
    }
}
