package dev.anvilcraft.fooding;


import dev.dubhe.anvilcraft.api.registry.AnvilCraftRegistrate;

public class AnvilCraftFooding {

    public static final String MOD_ID = "anvilcraft_fooding";

    public static final AnvilCraftRegistrate REGISTRATE = AnvilCraftRegistrate.create(MOD_ID);

    public static void init() {
        // common init
        // datagen init

        // fabric exclusive, squeeze this in here to register before stuff is used
        REGISTRATE.registerRegistrate();
    }
}
