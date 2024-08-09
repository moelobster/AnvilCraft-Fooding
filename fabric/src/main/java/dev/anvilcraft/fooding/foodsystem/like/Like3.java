package dev.anvilcraft.fooding.foodsystem.like;

import dev.dubhe.anvilcraft.init.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Like3 implements LikeType{
    static List<Item> jewelry = new ArrayList<>();
    public static void init(){
        jewelry.add(Items.REDSTONE);
        jewelry.add(Items.COAL);
        jewelry.add(Items.EMERALD);
        jewelry.add(Items.LAPIS_LAZULI);
        jewelry.add(Items.DIAMOND);
        jewelry.add(Items.QUARTZ);
        jewelry.add(Items.GLOWSTONE_DUST);
        jewelry.add(Items.GHAST_TEAR);
        jewelry.add(ModItems.GEODE.asItem());
        jewelry.add(ModItems.TOPAZ.asItem());
        jewelry.add(ModItems.RUBY.asItem());
        jewelry.add(ModItems.SAPPHIRE.asItem());
        jewelry.add(ModItems.AMBER.asItem());
    }
    @Override
    public void effect(ServerPlayer player, int times, int grade) {
        int l = 1;
        while (defaultNum * l <= times) {
            l = l + 1;
        }
        l = l - 1;
        for(Item item:jewelry){
            double chance = Math.random();
            if(chance < ( l /(l+29.0))){
                ItemStack itemStack = new ItemStack(item);
                itemStack.setCount(new Random().nextInt((int) Math.floor(Math.sqrt(grade)))+1);
                ItemEntity itemEntity = new ItemEntity(player.level(), player.xOld, player.yOld, player.zOld, itemStack);
                player.level().addFreshEntity(itemEntity);
            }
        }
    }
}
