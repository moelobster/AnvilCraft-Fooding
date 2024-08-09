package dev.anvilcraft.fooding.mixin;

import dev.anvilcraft.fooding.item.minecraft.FoodFix;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract void setTag(CompoundTag compoundTag);

    @Inject(method = "<init>(Lnet/minecraft/world/level/ItemLike;)V",at=@At("TAIL"))
    private void tag(ItemLike item, CallbackInfo ci){
        if(FoodFix.FoodList.containsKey(item.asItem())){
            this.setTag(FoodFix.FoodList.get(item.asItem()));
        }
    }
}
