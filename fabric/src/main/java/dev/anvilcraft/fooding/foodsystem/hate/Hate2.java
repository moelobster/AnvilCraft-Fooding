package dev.anvilcraft.fooding.foodsystem.hate;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.ArrayList;
import java.util.List;

public class Hate2 implements HateType {
    static List<MobEffect> effects = new ArrayList<>();
    public static void init(){
        effects.add(MobEffects.MOVEMENT_SLOWDOWN);
        effects.add(MobEffects.BLINDNESS);
        effects.add(MobEffects.UNLUCK);
        effects.add(MobEffects.BAD_OMEN);
    }
    @Override
    public void effect(ServerPlayer player, int times, int grade) {
        int l = 1;
        while (defaultNum * l <= times) {
            l = l + 1;
        }
        l = l - 1;
        for( MobEffect mobEffect: effects){
            if(Math.random() < (l/(l+29.0))){
                player.addEffect(new MobEffectInstance(mobEffect,100*grade,l));
            }
        }
    }
}
