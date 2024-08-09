package dev.anvilcraft.fooding.foodsystem;

import dev.anvilcraft.fooding.foodsystem.haste.*;
import dev.anvilcraft.fooding.foodsystem.like.*;
import dev.anvilcraft.fooding.foodsystem.normal.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static dev.anvilcraft.fooding.foodsystem.haste.Hate.*;
import static dev.anvilcraft.fooding.foodsystem.like.Like.*;
import static dev.anvilcraft.fooding.foodsystem.taste.Tastes.*;

public class FoodEfeects {
    static Map<String, TasteType> normalMap = new HashMap<>();
    static Map<String, LikeType> likeMap = new HashMap<>();
    static Map<String, HateType> hateMap = new HashMap<>();

    public static void init(){
        likeInit();
        normalInit();
        hateInit();
        mannerInit();
    }
    public static void normalInit(){
        normalMap.put(FRUIT.get(), new Fruit());
        normalMap.put(HOT.get(), new Hot());
        normalMap.put(FULL.get(), new Full());
        normalMap.put(LUXURIOUS.get(), new Luxurious());
        normalMap.put(MYSTERY.get(), new Mystery());
        normalMap.put(UNBELIEVABLE.get(), new Unbelievable());
        normalMap.put(POISON.get(), new Poison());
        normalMap.put(VEGETABLE.get(), new Vegetable());
        normalMap.put(RAW.get(),new Raw());
        normalMap.put(BAKED.get(), new Baked());
        normalMap.put(SWEET.get(), new Sweet());
        normalMap.put(SEAFOOD.get(), new Seafood());
        normalMap.put(MEAT.get(), new Meat());
        normalMap.put(CHOCOLATE.get(), new Chocolate());
        normalMap.put(SOUP.get(), new Soup());
        normalMap.put(MUSHROOM.get(), new Mushroom());
        normalMap.put(WARM.get(), new Warm());
        normalMap.put(MILK.get(), new Milk());
        normalMap.put(WESTERN.get(), new Western());
        normalMap.put(CREAM.get(), new Cream());
        normalMap.put(BITTER.get(), new Bitter());
    }
    public static void likeInit(){
        likeMap.put(LIKE1.get(), new Like1());
        likeMap.put(LIKE2.get(), new Like2());
        likeMap.put(LIKE3.get(), new Like3());
        likeMap.put(LIKE4.get(), new Like4());
        likeMap.put(LIKE5.get(), new Like5());
        likeMap.put(LIKE6.get(), new Like6());

    }
    public static void hateInit(){
        hateMap.put(HATE1.get(), new Hate1());
        hateMap.put(HATE2.get(), new Hate2());
        hateMap.put(HATE3.get(), new Hate3());
        hateMap.put(HATE4.get(), new Hate4());

    }
    public static void mannerInit(){
        Like1.init();
        Like2.init();
        Like3.init();
        Like5.init();
        Like6.init();
        Hate1.init();
        Hate2.init();
    }

    public static void foodEffects(ServerPlayer player, String key, CompoundTag tag, int grade){
        if(normalMap.containsKey(key)) {
            TasteType tasteType = normalMap.get(key);
            tasteType.effect(player);
            Set<String> keys = tag.getAllKeys();
            for(String k:keys){
                for (Like l:Like.values()){
                    if(l.get().equals(k)){
                        LikeType likeType = likeMap.get(k);
                        likeType.effect(player,tag.getInt(k),grade);
                        return;
                    }
                }
                for(Hate l:Hate.values()){
                    if(l.get().equals(k)){
                        HateType hateType = hateMap.get(k);
                        hateType.effect(player,tag.getInt(k),grade);
                        return;
                    }
                }
            }

        }
    }
}
