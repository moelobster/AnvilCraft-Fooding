package dev.anvilcraft.fooding.foodsystem.haste;

import net.minecraft.server.level.ServerPlayer;

public interface HateType {
    final int defaultNum = 4;
    void effect(ServerPlayer player, int times, int grade);
}
