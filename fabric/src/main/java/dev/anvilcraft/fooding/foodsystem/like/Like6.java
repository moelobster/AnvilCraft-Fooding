package dev.anvilcraft.fooding.foodsystem.like;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Like6 implements LikeType{
    static List<Item> sherdtList = new ArrayList<>();
    public static void init(){
        sherdtList.add(Items.ANGLER_POTTERY_SHERD);
        sherdtList.add(Items.ARCHER_POTTERY_SHERD);
        sherdtList.add(Items.ARMS_UP_POTTERY_SHERD);
        sherdtList.add(Items.BLADE_POTTERY_SHERD);
        sherdtList.add(Items.BREWER_POTTERY_SHERD);
        sherdtList.add(Items.BURN_POTTERY_SHERD);
        sherdtList.add(Items.DANGER_POTTERY_SHERD);
        sherdtList.add(Items.EXPLORER_POTTERY_SHERD);
        sherdtList.add(Items.FRIEND_POTTERY_SHERD);
        sherdtList.add(Items.HEART_POTTERY_SHERD);
        sherdtList.add(Items.HEARTBREAK_POTTERY_SHERD);
        sherdtList.add(Items.HOWL_POTTERY_SHERD);
        sherdtList.add(Items.MINER_POTTERY_SHERD);
        sherdtList.add(Items.MOURNER_POTTERY_SHERD);
        sherdtList.add(Items.PLENTY_POTTERY_SHERD);
        sherdtList.add(Items.PRIZE_POTTERY_SHERD);
        sherdtList.add(Items.SHEAF_POTTERY_SHERD);
        sherdtList.add(Items.SHELTER_POTTERY_SHERD);
        sherdtList.add(Items.SKULL_POTTERY_SHERD);
        sherdtList.add(Items.SNORT_POTTERY_SHERD);
    }
    @Override
    public void effect(ServerPlayer player, int times, int grade) {
        int l = 1;
        while (defaultNum * l <= times) {
            l = l + 1;
        }
        l = l - 1;
        for(Item item:sherdtList){
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
