package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class Warm implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        float a = player.getHealth();
        player.setHealth(a + 1);
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,60,0));
    }
}
