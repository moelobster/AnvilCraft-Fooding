package dev.anvilcraft.fooding.block.entity;

import dev.anvilcraft.fooding.block.entity.inventory.FoodAnalyzerMenu;
import dev.anvilcraft.fooding.init.ModMenuTypes;
import dev.dubhe.anvilcraft.api.depository.FilteredItemDepository;
import dev.dubhe.anvilcraft.api.depository.fabric.ItemDepositoryHelperImpl;
import dev.dubhe.anvilcraft.api.power.IPowerConsumer;
import dev.dubhe.anvilcraft.api.power.PowerGrid;
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
public class FoodAnalyzerBlockEntity extends BaseMachineBlockEntity implements
         IPowerConsumer{
    @Getter
    @Setter
    private PowerGrid grid;


    private final FilteredItemDepository depository = new FilteredItemDepository.Pollable(2) {
        @Override
        public void onContentsChanged(int slot) {
            setChanged();
        }
    };
    protected FoodAnalyzerBlockEntity(BlockEntityType<? extends BlockEntity> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static FoodAnalyzerBlockEntity createBlockEntity(
            BlockEntityType<?> type, BlockPos pos, BlockState blockState
    ) {
        return new FoodAnalyzerBlockEntity(type, pos, blockState);
    }

    public static void onBlockEntityRegister(BlockEntityType<FoodAnalyzerBlockEntity> type) {
        ItemStorage.SIDED.registerForBlockEntity(
                (blockEntity, direction) -> ItemDepositoryHelperImpl.toStorage(blockEntity.getDepository()), type
        );
    }


    private boolean canCraft() {
        return grid != null && grid.isWork();
    }

    public void craft(@NotNull Level level){
        if(!canCraft()) return;
        ItemStack itemStack1 = this.depository.getStack(0);
        ItemStack itemStack2 = this.depository.getStack(1);
        if (itemStack1.isEmpty()||itemStack2.isEmpty())  return;
        if (itemStack1.getTagElement(MOD_ID)==null||itemStack2.getItem()!= Items.WRITABLE_BOOK)  return;
        ListTag pages = new ListTag();
        Set<String> keys = itemStack1.getTagElement(MOD_ID).getAllKeys();
        for (String key : keys){
            pages.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(key+":"+itemStack1.getTagElement(MOD_ID).getInt(key)))));
        }
        CompoundTag bookTag = new CompoundTag();
        bookTag.put("pages",pages);
        itemStack2.setTag(bookTag);
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
        return Direction.UP;
    }

    @Override
    public void setDirection(Direction direction) {

    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.anvilcraft_fooding:food_analyzer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
        return new FoodAnalyzerMenu(ModMenuTypes.FOOD_ANALYZER.get(),i,inventory,this);
    }
    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Inventory")) {
            this.depository.deserializeNbt(tag.getCompound("Inventory"));
        }
    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", this.depository.serializeNbt());
    }

}
