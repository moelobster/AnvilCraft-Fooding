package dev.anvilcraft.fooding.data.generator;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;

public class SauteRecipeLoader {
    public static RegistrateRecipeProvider provider = null;
    public static void init(RegistrateRecipeProvider provider){
        SauteRecipeLoader.provider = provider ;

    }
}
