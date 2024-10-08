package dev.anvilcraft.fooding.client.gui.screen;

import dev.anvilcraft.fooding.block.entity.inventory.FoodAnalyzerMenu;
import dev.anvilcraft.fooding.block.entity.screen.NoneDirectionMachineScreen;
import dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric;
import dev.dubhe.anvilcraft.client.gui.component.OutputDirectionButton;
import dev.dubhe.anvilcraft.client.gui.screen.inventory.BaseMachineScreen;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class FoodAnalyzerScreen extends NoneDirectionMachineScreen<FoodAnalyzerMenu> {
    @Getter
    private OutputDirectionButton directionButton = null;
    private static final ResourceLocation CONTAINER_LOCATION = AnvilCraftFoodingFabric.of("textures/gui/food_analyzer.png");
    private final FoodAnalyzerMenu menu;

    public FoodAnalyzerScreen(FoodAnalyzerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.menu = menu;
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(CONTAINER_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
    @Override
    protected void init() {
        super.init();
    }
}
