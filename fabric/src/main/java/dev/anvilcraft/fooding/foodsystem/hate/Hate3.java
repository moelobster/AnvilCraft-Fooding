package dev.anvilcraft.fooding.foodsystem.hate;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class Hate3 implements HateType {
    @Override
    public void effect(ServerPlayer player, int times, int grade) {
        int l = 1;
        while (defaultNum * l <= times) {
            l = l + 1;
        }
        l = l - 1;
        Level level = player.level();
        if(Math.random() < (l/(l+29.0))){
            if (!level.isClientSide) {
                Vec3 playerPos = player.position();

                BlockPos spawnPos = new BlockPos((int) Math.floor(playerPos.x),(int) Math.floor (playerPos.y),(int) Math.floor( playerPos.z));

                Zombie zombie = new Zombie(EntityType.ZOMBIE, level);

                zombie.moveTo(spawnPos, 0.0F, 0.0F);

                Objects.requireNonNull(zombie.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(2000.0*l*grade);

                zombie.setHealth(zombie.getMaxHealth());

                level.addFreshEntity(zombie);
            }
        }
    }
}
