package dev.anvilcraft.fooding.foodsystem;

import net.minecraft.nbt.CompoundTag;

public interface FoodsData {
    CompoundTag getFoodsData();
    void setFoodsData(CompoundTag customData);
}
