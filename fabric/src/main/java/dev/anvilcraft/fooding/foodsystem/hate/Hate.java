package dev.anvilcraft.fooding.foodsystem.hate;

import java.util.Random;

public enum Hate {
    HATE1("hate1"),
    HATE2("hate2"),
    HATE3("hate3"),
    HATE4("hate4")
    ;
    private final String haste;

    Hate(String name) {
        this.haste = name;
    }
    public String get(){
        return haste;
    }
    public String random(){
        int i = new Random().nextInt(Hate.values().length);
        return Hate.values()[i].get();
    }
}
