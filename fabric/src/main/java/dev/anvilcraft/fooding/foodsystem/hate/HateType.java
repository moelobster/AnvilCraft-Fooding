package dev.anvilcraft.fooding.foodsystem.hate;

import net.minecraft.server.level.ServerPlayer;

public interface HateType {
    int defaultNum = 4;
    void effect(ServerPlayer player, int times, int grade);
}
