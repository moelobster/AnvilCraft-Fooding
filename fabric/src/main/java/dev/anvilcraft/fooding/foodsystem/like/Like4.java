package dev.anvilcraft.fooding.foodsystem.like;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class Like4 implements LikeType{
    @Override
    public void effect(ServerPlayer player, int times, int grade) {
        int l = 1;
        while (defaultNum * l <= times) {
            l = l + 1;
        }
        l = l - 1;
        int i = 0;
        for(EquipmentSlot equipmentSlot: EquipmentSlot.values()){
            if(equipmentSlot.getType() == EquipmentSlot.Type.ARMOR){
                ItemStack itemStack = player.kjs$getEquipment(equipmentSlot);
                if(!itemStack.isEmpty()){
                    i = i + 1;
                    int newDamage = Math.min(5*l,itemStack.getMaxDamage()-itemStack.getDamageValue());
                    itemStack.setDamageValue(newDamage);
                }
            }
            if(i >= grade){
                break;
            }
        }
    }
}
