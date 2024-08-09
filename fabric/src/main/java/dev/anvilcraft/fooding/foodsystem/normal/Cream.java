package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;

public class Cream implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        FoodData foodData = player.getFoodData();
        foodData.eat(1,3.0f);
    }
}
