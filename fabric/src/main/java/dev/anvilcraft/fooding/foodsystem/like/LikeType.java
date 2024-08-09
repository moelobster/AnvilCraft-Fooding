package dev.anvilcraft.fooding.foodsystem.like;

import net.minecraft.server.level.ServerPlayer;

public interface LikeType {
    final int defaultNum = 4;
    void effect(ServerPlayer player, int times,int grade);
}
