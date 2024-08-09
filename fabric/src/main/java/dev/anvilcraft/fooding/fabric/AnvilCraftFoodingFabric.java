package dev.anvilcraft.fooding.fabric;

import dev.anvilcraft.fooding.event.FoodCraftEventListener;
import dev.anvilcraft.fooding.foodsystem.FoodEfeects;
import dev.anvilcraft.fooding.init.ModFoods;
import dev.anvilcraft.fooding.init.ModItemGroups;
import dev.anvilcraft.fooding.data.recipe.FoodCraftRecipe;
import dev.anvilcraft.fooding.item.minecraft.FoodFix;
import dev.anvilcraft.lib.event.EventManager;
import dev.dubhe.anvilcraft.AnvilCraft;
import dev.dubhe.anvilcraft.api.registry.AnvilCraftRegistrate;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class AnvilCraftFoodingFabric implements ModInitializer {
    public static final String MOD_ID = "anvilcraft_fooding";
    public static final EventManager EVENT_BUS = new EventManager(AnvilCraft.EVENT_BUS);


    public static final AnvilCraftRegistrate REGIS = AnvilCraftRegistrate.create(MOD_ID);



    public static void init() {
        // common init
        ModFoods.init();
        FoodFix.init();

        FoodCraftRecipe.init();
        ModItemGroups.init();
        FoodEfeects.init();

        // event init
        EVENT_BUS.register(new FoodCraftEventListener());
//        EVENT_BUS.register(new FoodRecipeListener());
        // fabric exclusive, squeeze this in here to register before stuff is used
        REGIS.registerRegistrate();
    }
    public static @NotNull ResourceLocation of(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    @Override
    public void onInitialize() {
        AnvilCraftFoodingFabric.init();
    }


}