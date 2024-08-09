package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodData;

public class Meat implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        FoodData foodData = player.getFoodData();
        foodData.eat(1,2.0f);
        player.addEffect(new MobEffectInstance(MobEffects.SATURATION,40,0));
    }
}
