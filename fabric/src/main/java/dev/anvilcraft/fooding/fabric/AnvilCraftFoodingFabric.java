package dev.anvilcraft.fooding.fabric;

import dev.anvilcraft.fooding.event.FoodCraftEventListener;
import dev.anvilcraft.fooding.foodsystem.FoodEfects;
import dev.anvilcraft.fooding.init.*;
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



    public static void register() {
        // common init
        ModFoods.register();
        FoodFix.register();
        ModBlocks.register();
        ModBlockEntities.register();
        ModMenuTypes.register();

        FoodCraftRecipe.register();
        ModItemGroups.register();
        FoodEfects.register();

        // event init
        EVENT_BUS.register(new FoodCraftEventListener());
        REGIS.registerRegistrate();
    }
    public static @NotNull ResourceLocation of(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    @Override
    public void onInitialize() {
        AnvilCraftFoodingFabric.register();
    }


}