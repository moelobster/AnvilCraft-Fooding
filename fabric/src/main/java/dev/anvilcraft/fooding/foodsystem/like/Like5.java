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

public class Like5 implements LikeType{
    static List<Item> plantList = new ArrayList<>();
    public static void init(){
        plantList.add(Items.MOSS_BLOCK);
        plantList.add(Items.MOSS_CARPET);
        plantList.add(Items.OAK_LEAVES);
        plantList.add(Items.OAK_SAPLING);
        plantList.add(Items.BIRCH_LEAVES);
        plantList.add(Items.BIRCH_SAPLING);
        plantList.add(Items.SPRUCE_LEAVES);
        plantList.add(Items.SPRUCE_SAPLING);
        plantList.add(Items.JUNGLE_LEAVES);
        plantList.add(Items.JUNGLE_SAPLING);
        plantList.add(Items.ACACIA_LEAVES);
        plantList.add(Items.ACACIA_SAPLING);
        plantList.add(Items.DARK_OAK_LEAVES);
        plantList.add(Items.DARK_OAK_SAPLING);
        plantList.add(Items.MANGROVE_LEAVES);
        plantList.add(Items.MANGROVE_PROPAGULE);
        plantList.add(Items.CHERRY_LEAVES);
        plantList.add(Items.CHERRY_SAPLING);
        plantList.add(Items.AZALEA_LEAVES);
        plantList.add(Items.FLOWERING_AZALEA_LEAVES);
        plantList.add(Items.AZALEA);
        plantList.add(Items.FLOWERING_AZALEA);
        plantList.add(Items.BAMBOO);
    }
    @Override
    public void effect(ServerPlayer player, int times, int grade) {
        int l = 1;
        while (defaultNum * l <= times) {
            l = l + 1;
        }
        l = l - 1;
        for(Item item:plantList){
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
