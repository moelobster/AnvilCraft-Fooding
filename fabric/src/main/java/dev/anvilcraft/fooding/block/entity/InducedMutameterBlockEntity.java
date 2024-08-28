package dev.anvilcraft.fooding.block.entity;

import dev.anvilcraft.fooding.block.InducedMutameterBlock;
import dev.anvilcraft.fooding.init.ModBlocks;
import dev.dubhe.anvilcraft.api.depository.FilteredItemDepository;
import dev.dubhe.anvilcraft.api.depository.fabric.ItemDepositoryHelperImpl;
import dev.dubhe.anvilcraft.api.power.IPowerConsumer;
import dev.dubhe.anvilcraft.api.power.PowerGrid;
import dev.dubhe.anvilcraft.block.AutoCrafterBlock;
import dev.dubhe.anvilcraft.block.entity.BaseMachineBlockEntity;
import lombok.Getter;
import lombok.Setter;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.MOD_ID;

@Getter
public class InducedMutameterBlockEntity extends BaseMachineBlockEntity implements
        IPowerConsumer {
    @Setter
    private PowerGrid grid;
    private static final int POWER = 64;
    private int cooldown = 20;
    private final FilteredItemDepository depository = new FilteredItemDepository.Pollable(0) {
        @Override
        public void onContentsChanged(int slot) {
            setChanged();
        }
    };
    protected InducedMutameterBlockEntity(BlockEntityType<? extends BlockEntity> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }
    public static InducedMutameterBlockEntity createBlockEntity(
            BlockEntityType<?> type, BlockPos pos, BlockState blockState
    ) {
        return new InducedMutameterBlockEntity(type, pos, blockState);
    }

    public static void onBlockEntityRegister(BlockEntityType<InducedMutameterBlockEntity> type) {
        ItemStorage.SIDED.registerForBlockEntity(
                (blockEntity, direction) -> ItemDepositoryHelperImpl.toStorage(blockEntity.getDepository()), type
        );
    }
    public void tick(@NotNull Level level, BlockPos pos) {
        if (cooldown > 0) {
            cooldown--;
        }
    }

    private boolean canCraft() {
        if (grid == null || !grid.isWork()) return false;
        if (cooldown > 0) return false;
        return true;
    }
    public void craft(@NotNull Level level){
        if(!canCraft()) return;

    }
    @Override
    public Level getCurrentLevel() {
        return this.getLevel();
    }

    @Override
    public @NotNull BlockPos getPos() {
        return this.getBlockPos();
    }

    @Override
    public Direction getDirection() {
        if (this.level == null) return Direction.UP;
        BlockState state = this.level.getBlockState(this.getBlockPos());
        if (state.is(ModBlocks.INDUCED_MUTAMETER.get())) return state.getValue(InducedMutameterBlock.FACING);
        return Direction.UP;
    }

    @Override
    public void setDirection(Direction direction) {
        BlockPos pos = this.getBlockPos();
        Level level = this.getLevel();
        if (null == level) return;
        BlockState state = level.getBlockState(pos);
        if (!state.is(ModBlocks.INDUCED_MUTAMETER.get())) return;
        level.setBlockAndUpdate(pos, state.setValue(InducedMutameterBlock.FACING, direction));
    }
    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        depository.deserializeNbt(tag.getCompound("Inventory"));
        cooldown = tag.getInt("Cooldown");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", this.depository.serializeNbt());
        tag.putInt("Cooldown", cooldown);
    }



    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
        return null;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.anvilcraft_fooding:induced_mutameter");
    }
}
