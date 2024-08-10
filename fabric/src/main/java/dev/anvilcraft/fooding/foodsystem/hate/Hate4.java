package dev.anvilcraft.fooding.foodsystem.hate;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class Hate4 implements HateType {
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
                ItemStack itemStack = player.getItemBySlot(equipmentSlot);
                if(!itemStack.isEmpty()){
                    i = i + 1;
                    int newDamage = Math.max(0,itemStack.getDamageValue()-5*l);
                    itemStack.setDamageValue(newDamage);
                }
            }
            if(i >= grade){
                break;
            }
        }
    }
}
