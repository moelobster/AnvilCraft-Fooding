package dev.anvilcraft.fooding.foodsystem.like;

import dev.dubhe.anvilcraft.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Like1 implements LikeType{

    /**
     *  矿物小涌泉
     */

    static List<Item> oreList = new ArrayList<>();
    public static void init(){
        oreList.add(Items.IRON_NUGGET);
        oreList.add(Items.GOLD_NUGGET);
        oreList.add(Items.AMETHYST_SHARD);
        oreList.add(Items.NETHERITE_SCRAP);
        oreList.add(ModItems.ROYAL_STEEL_NUGGET.asItem());
        oreList.add(ModItems.EMBER_METAL_NUGGET.asItem());
        oreList.add(ModItems.CURSED_GOLD_NUGGET.asItem());
        oreList.add(ModItems.COPPER_NUGGET.asItem());
        oreList.add(ModItems.ZINC_NUGGET.asItem());
        oreList.add(ModItems.TIN_NUGGET.asItem());
        oreList.add(ModItems.TITANIUM_NUGGET.asItem());
        oreList.add(ModItems.TUNGSTEN_NUGGET.asItem());
        oreList.add(ModItems.LEAD_NUGGET.asItem());
        oreList.add(ModItems.SILVER_NUGGET.asItem());
        oreList.add(ModItems.URANIUM_NUGGET.asItem());
        oreList.add(ModItems.EARTH_CORE_SHARD.asItem());
    }
    @Override
    public void effect(ServerPlayer player, int times,int grade) {
        int l = 1;
        while (defaultNum * l <= times) {
            l = l + 1;
        }
        l = l - 1;
        for(Item item:oreList){
            double chance = Math.random();
            if(chance <= (l/(l+19.0))){
                ItemStack itemStack = new ItemStack(item);
                itemStack.setCount(new Random().nextInt((int) Math.floor(Math.sqrt(grade)))+1);
                ItemEntity itemEntity = new ItemEntity(player.level(), player.xOld, player.yOld, player.zOld, itemStack);
                player.level().addFreshEntity(itemEntity);
            }
        }

    }
}
