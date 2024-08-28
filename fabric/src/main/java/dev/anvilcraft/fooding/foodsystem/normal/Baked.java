package dev.anvilcraft.fooding.foodsystem.normal;

import dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;

public class Baked implements TasteType{
    @Override
    public void effect(ServerPlayer player) {
        FoodData foodData = player.getFoodData();
        foodData.eat(0,2.0f);
    }
}
