package dev.anvilcraft.fooding.init;

import com.tterrag.registrate.util.entry.MenuEntry;
import dev.anvilcraft.fooding.block.entity.inventory.FoodAnalyzerMenu;
import dev.anvilcraft.fooding.client.gui.screen.FoodAnalyzerScreen;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.REGIS;

public class ModMenuTypes {
    public static final MenuEntry<FoodAnalyzerMenu> FOOD_ANALYZER = REGIS
            .menu("food_analyzer",FoodAnalyzerMenu::new,()-> FoodAnalyzerScreen::new)
            .register();
    public static void register(){
    }
}
