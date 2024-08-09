package dev.anvilcraft.fooding.foodsystem.like;

import java.util.Random;

public enum Like {
    NONE("none"),
    LIKE1("like1"),
    LIKE2("like2"),
    LIKE3("like3"),
    LIKE4("like4"),
    LIKE5("like5"),
    LIKE6("like6")

    ;
    private final String like;



    Like(String name) {
        this.like = name;
    }
    public String get(){
        return like;
    }
    public String random(){
        int i = new Random().nextInt(Like.values().length-1);
        return Like.values()[i+1].get();
    }
}
