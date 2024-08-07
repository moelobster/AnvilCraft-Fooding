package dev.anvilcraft.fooding.init.data;

import net.minecraft.world.food.FoodProperties;

public class ModFoodHunger {
    public static final FoodProperties CHILI = new FoodProperties.Builder().nutrition(4).saturationMod(2.4f).build();
    public static final FoodProperties RICE = new FoodProperties.Builder().nutrition(4).saturationMod(2.4f).build();
    public static final FoodProperties RICE_WITH_CHILI = new FoodProperties.Builder().nutrition(6).saturationMod(3.0f).build();

}
