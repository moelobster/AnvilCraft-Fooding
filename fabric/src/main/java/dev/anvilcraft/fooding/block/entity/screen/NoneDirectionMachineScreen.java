package dev.anvilcraft.fooding.block.entity.screen;

import dev.dubhe.anvilcraft.client.gui.component.OutputDirectionButton;
import dev.dubhe.anvilcraft.client.gui.screen.inventory.BaseMachineScreen;
import dev.dubhe.anvilcraft.network.MachineOutputDirectionPack;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.BiFunction;

@Getter
public abstract class NoneDirectionMachineScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    private final Player player;

    /**
     * 基本机器 GUI
     *
     * @param menu      菜单
     * @param inventory 玩家背包
     * @param title     标题
     */
    public NoneDirectionMachineScreen(T menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.player = inventory.player;
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
