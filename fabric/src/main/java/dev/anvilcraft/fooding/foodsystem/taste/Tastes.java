package dev.anvilcraft.fooding.foodsystem.taste;

public enum Tastes {
    FRUIT("fruit"),//水果
    HOT("hot"),//辣
    FULL("full"),//饱腹
    LUXURIOUS("luxurious"),//奢华
    MYSTERY("mystery"),//神秘
    UNBELIEVABLE("unbelievable"),//不可思议的
    VEGETABLE("vegetable"),//素
    RAW("raw"),//生
    BAKED("baked"),//烤制的
    POISON("poison"),//有毒的
    SWEET("sweet"),//甜
    SEAFOOD("seafood"),//海产
    MEAT("meat"),//肉
    CHOCOLATE("chocolate"),//可可
    SOUP("soup"),//汤羹
    MUSHROOM("mushroom"),//蘑菇
    WARM("warm"),//暖
    MILK("milk"),//牛奶
    WESTERN("western"),///西式
    CREAM("cream"),//奶油
    BITTER("bitter")//苦
    ;


    private final String taste;

    Tastes(String taste) {
        this.taste = taste;
    }
    public String get(){
        return taste;
    }
}
