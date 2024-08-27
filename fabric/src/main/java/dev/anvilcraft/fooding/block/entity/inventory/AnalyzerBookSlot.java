package dev.anvilcraft.fooding.block.entity.inventory;

import dev.dubhe.anvilcraft.api.depository.IItemDepository;
import dev.dubhe.anvilcraft.api.depository.ItemDepositorySlot;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class AnalyzerBookSlot extends ItemDepositorySlot {
    private final FoodAnalyzerMenu menu;
    public AnalyzerBookSlot(IItemDepository container, int slot, int x, int y, FoodAnalyzerMenu menu) {
        super( container, slot, x, y);
        this.menu = menu;
    }
    @Override
    public boolean mayPlace(@NotNull ItemStack stack){
        return this.menu.isBook(stack);
    }
}
