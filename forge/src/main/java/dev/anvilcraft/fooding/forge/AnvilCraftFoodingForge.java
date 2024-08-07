package dev.anvilcraft.fooding.forge;

import dev.anvilcraft.fooding.AnvilCraftFooding;
import net.minecraftforge.fml.common.Mod;

@Mod(AnvilCraftFooding.MOD_ID)
public class AnvilCraftFoodingForge {
    public AnvilCraftFoodingForge() {
        AnvilCraftFooding.init();
    }
}