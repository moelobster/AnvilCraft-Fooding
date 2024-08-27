package dev.anvilcraft.fooding.block.entity.inventory;

import dev.anvilcraft.fooding.block.entity.FoodAnalyzerBlockEntity;
import dev.anvilcraft.fooding.init.ModBlocks;
import dev.dubhe.anvilcraft.api.depository.ItemDepositorySlot;
import dev.dubhe.anvilcraft.inventory.BaseMachineMenu;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@Getter
public class FoodAnalyzerMenu extends BaseMachineMenu implements ContainerListener {
    public final FoodAnalyzerBlockEntity blockEntity;
    private final Slot resultSlot;
    private final Level level;
    public FoodAnalyzerMenu(@Nullable MenuType<?> menuType, int containerId, Inventory inventory, @NotNull FriendlyByteBuf extraData) {
        this(menuType, containerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()));
    }
    public FoodAnalyzerMenu(MenuType<?> menuType, int containerId, Inventory inventory, BlockEntity blockEntity){
        super(menuType, containerId, blockEntity);
        FoodAnalyzerMenu.checkContainerSize(inventory,1);
        this.blockEntity = (FoodAnalyzerBlockEntity) blockEntity;
        this.level = inventory.player.level();

        this.addPlayerInventory(inventory);
        this.addPlayerHotbar(inventory);

        this.addSlot( new ItemDepositorySlot(this.blockEntity.getDepository(),0,54,36));
        this.addSlot(resultSlot = new AnalyzerBookSlot(this.blockEntity.getDepository(),1,108,36,this));

//        this.onChanged();
        this.addSlotListener(this);

    }
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 2;

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        final ItemStack copyOfSourceStack = sourceStack.copy();
        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (moveItemToActiveSlot(sourceStack)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(
                    sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false
            )) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }
    // 移动物品到可用槽位
    private boolean moveItemToActiveSlot(ItemStack stack) {
        int count = stack.getCount();
        for (int index = FoodAnalyzerMenu.TE_INVENTORY_FIRST_SLOT_INDEX; index < slots.size(); index++) {
            // 只有对应槽位可以放入物品时才向槽位里快速移动物品
            if (canPlace(stack, index)) {
                moveItemStackTo(stack, index, index + 1, false);
                if (stack.isEmpty()) {
                    break;
                }
            }
        }
        return stack.getCount() >= count;
    }

    // 是否可以向槽位中放入物品
    private boolean canPlace(ItemStack stack, int index) {
        if(!slots.get(index).hasItem()){
            return true;
        }
        return slots.get(index).getItem() == stack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(
                ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.FOOD_ANALYZER.get()
        );
    }

    @Override
    public void slotChanged(@NotNull AbstractContainerMenu containerToSend, int dataSlotIndex, @NotNull ItemStack stack) {

    }

    @Override
    public void dataChanged(@NotNull AbstractContainerMenu containerMenu, int dataSlotIndex, int value) {

    }

    public boolean isBook(ItemStack itemStack){
        return itemStack.is(Items.WRITABLE_BOOK);
    }
}
