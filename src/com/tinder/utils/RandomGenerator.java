package com.tinder.utils;

import java.util.Random;

public class RandomGenerator {
    private final static Random random = new Random();

    public static int getRandom(){
        return getRandom(4);
    }
    public static int getRandom(int bound){
        return random.nextInt();
    }
}
