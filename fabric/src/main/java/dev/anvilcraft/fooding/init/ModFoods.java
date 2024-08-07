package dev.anvilcraft.fooding.init;


import com.tterrag.registrate.util.entry.ItemEntry;
import dev.anvilcraft.fooding.init.data.ModFoodHunger;
import dev.anvilcraft.fooding.item.FoodItem;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.REGIS;

public class ModFoods {




    public static final ItemEntry<? extends Item> CHILI = REGIS
            .item("chili",properties -> {
                CompoundTag tag = new CompoundTag();
                tag.putInt("hot",1);
                return new FoodItem(properties,tag);
            })
            .properties(properties ->  properties.food(ModFoodHunger.CHILI))
            .register();


    public static final ItemEntry<? extends Item> RICE = REGIS
            .item("rice",properties -> {
                CompoundTag tag = new CompoundTag();
                tag.putInt("full",1);
                return new FoodItem(properties,tag);
            })
            .properties(properties ->  properties.food(ModFoodHunger.RICE))
            .register();
    public static final ItemEntry<? extends Item> RICE_WITH_CHILI = REGIS
            .item("rice_with_chili",properties -> {
                CompoundTag tag = new CompoundTag();
                tag.putInt("hot",1);
                tag.putInt("full",1);
                return new FoodItem(properties,tag);
            })
            .properties(properties ->  properties.food(ModFoodHunger.RICE_WITH_CHILI))
            .register();



    public static void register() {


    }

}
