package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;

public class Milk implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        if(player.hasEffect(MobEffects.POISON)){
            player.removeEffect(MobEffects.POISON);
        }
        if(player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)){
            player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        }
        if(player.hasEffect(MobEffects.WEAKNESS)){
            player.removeEffect(MobEffects.WEAKNESS);
        }
    }
}
