package dev.anvilcraft.fooding.foodsystem.normal;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;

public class Full implements TasteType{


    @Override
    public void effect(ServerPlayer player) {
        FoodData foodData = player.getFoodData();
        foodData.eat(2,0.2f);
    }
}
