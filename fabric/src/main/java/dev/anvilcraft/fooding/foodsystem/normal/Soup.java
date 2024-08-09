package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;

public class Soup implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        if(player.hasEffect(MobEffects.POISON)){
            player.removeEffect(MobEffects.POISON);
        }
    }
}
