package dev.anvilcraft.fooding.foodsystem.haste;

import java.util.Random;

public enum Hate {
    HATE1("haste1"),
    HATE2("haste2"),
    HATE3("haste3"),
    HATE4("haste4")
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
