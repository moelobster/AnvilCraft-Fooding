package dev.anvilcraft.fooding.data.recipe;

import dev.anvilcraft.fooding.data.recipe.outcome.FoodCraftSpawnItem;
import dev.anvilcraft.fooding.data.recipe.predicate.FoodCraftHasItem;
import dev.dubhe.anvilcraft.data.recipe.anvil.RecipeOutcome;
import dev.dubhe.anvilcraft.data.recipe.anvil.RecipePredicate;

public class FoodCraftRecipe {
    public static void init(){
        RecipePredicate.register("foodcraft_has_item", FoodCraftHasItem::new, FoodCraftHasItem::new);
        RecipeOutcome.register("foodcraft_spawn_item", FoodCraftSpawnItem::new, FoodCraftSpawnItem::new);
    }
}
