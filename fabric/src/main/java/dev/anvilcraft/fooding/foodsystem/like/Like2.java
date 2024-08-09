package dev.anvilcraft.fooding.foodsystem.like;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Like2 implements LikeType{
    static List<Item> flowerList = new ArrayList<>();
    public static void init(){
        flowerList.add(Items.DANDELION);
        flowerList.add(Items.POPPY);
        flowerList.add(Items.BLUE_ORCHID);
        flowerList.add(Items.ALLIUM);
        flowerList.add(Items.AZURE_BLUET);
        flowerList.add(Items.RED_TULIP);
        flowerList.add(Items.ORANGE_TULIP);
        flowerList.add(Items.WHITE_TULIP);
        flowerList.add(Items.PINK_TULIP);
        flowerList.add(Items.OXEYE_DAISY);
        flowerList.add(Items.CORNFLOWER);
        flowerList.add(Items.LILY_OF_THE_VALLEY);
        flowerList.add(Items.TORCHFLOWER);
        flowerList.add(Items.WITHER_ROSE);
        flowerList.add(Items.PINK_PETALS);
        flowerList.add(Items.SPORE_BLOSSOM);
        flowerList.add(Items.SUNFLOWER);
        flowerList.add(Items.LILAC);
        flowerList.add(Items.ROSE_BUSH);
        flowerList.add(Items.PEONY);
        flowerList.add(Items.PITCHER_PLANT);

    }
    @Override
    public void effect(ServerPlayer player, int times, int grade) {
        int l = 1;
        while (defaultNum * l <= times) {
            l = l + 1;
        }
        l = l - 1;
        for(Item item:flowerList){
            double chance = Math.random();
            if(chance < ( l /(l+19.0))){
                ItemStack itemStack = new ItemStack(item);
                itemStack.setCount(new Random().nextInt((int) Math.floor(Math.sqrt(grade)))+1);
                ItemEntity itemEntity = new ItemEntity(player.level(), player.xOld, player.yOld, player.zOld, itemStack);
                player.level().addFreshEntity(itemEntity);
            }
        }
    }
}
