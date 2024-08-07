package dev.anvilcraft.fooding.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.MOD_ID;


public class FoodItem extends Item {
    private CompoundTag foodTag = new CompoundTag();

    public FoodItem(Properties properties,CompoundTag compoundTag) {
        super(properties);
        foodTag.put(MOD_ID,compoundTag);
    }

    @Override
    public @NotNull ItemStack getDefaultInstance(){
        ItemStack stack = super.getDefaultInstance();
        stack.setTag(foodTag);
        return stack;
    }
}
