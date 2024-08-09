package dev.anvilcraft.fooding.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.jetbrains.annotations.NotNull;


import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.REGIS;

public class ModItemGroups {
    public static final RegistryEntry<CreativeModeTab> ANVILCRAFT_FOODING = REGIS
            .defaultCreativeTab("fooding",builder -> builder
                    .icon(ModFoods.RICE_WITH_CHILI::asStack)
                    .displayItems((ctx,entries)->{
                        entries.accept(ModFoods.CHILI.get().getDefaultInstance());
                        entries.accept(ModFoods.RICE_WITH_CHILI.get().getDefaultInstance());
                        entries.accept(ModFoods.RICE.get().getDefaultInstance());
                    })
                    .title(REGIS.addLang("itemGroup", AnvilCraftFoodingFabric.of("tools"), "AnvilCraft_Fooding:food"))
                    .build()
            )
            .register();


    public static void init() {
    }
    private static @NotNull ItemStack createMaxLevelBook(@NotNull RegistryEntry<? extends Enchantment> enchantment) {
        return EnchantedBookItem.createForEnchantment(
                new EnchantmentInstance(enchantment.get(), enchantment.get().getMaxLevel())
        );
    }
}
