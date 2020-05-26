package com.app.user.utils;

import java.util.Random;

public class UUIDutils {
    public static Random random = new Random(System.currentTimeMillis());
    public static String randomNumber() {
        String prevoir = "";
        String rear = "";
        for (int i = 0; i < 3; i++) {
            int r = random.nextInt(26) + 65;
            char c = (char) r;
            prevoir += c;
        }
        for (int i = 0; i < 8; i++) {
            int r = random.nextInt(10);
            rear += r;
        }
        return prevoir+rear;
    }

    public static String createToken() {
        String token = "";
        for (int i = 0; i < 20; i++) {
            int r = random.nextInt(10);
            token += r;
        }
        return token;
    }

//    public static void main(String[] args) {
//        System.out.println(randomNumber());
//    }
}
