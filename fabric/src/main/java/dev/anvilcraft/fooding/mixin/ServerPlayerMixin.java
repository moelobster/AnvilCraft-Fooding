package dev.anvilcraft.fooding.mixin;

import dev.anvilcraft.fooding.foodsystem.FoodsData;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Setter
@Getter
@Mixin(ServerPlayer.class)
public class ServerPlayerMixin implements FoodsData {
    @Unique
    private CompoundTag foodsData;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo info) {
        this.foodsData = new CompoundTag();
    }
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readFoodsData(CompoundTag compound, CallbackInfo info) {
        if (compound.contains("FoodsData", 10)) {
            this.foodsData = compound.getCompound("FoodsData");
        } else {
            this.foodsData = new CompoundTag();
        }
    }
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void addFoodsData(CompoundTag compound, CallbackInfo info) {
        if (this.foodsData != null) {
            compound.put("FoodsData", this.foodsData);
        }
    }
    @Inject(method = "restoreFrom",at = @At("TAIL"))
    private void onPlayerClone(ServerPlayer oldPlayer, boolean alive, CallbackInfo info) {
        if (!alive) {
            this.foodsData = ((FoodsData) oldPlayer).getFoodsData();
        }
    }
}
