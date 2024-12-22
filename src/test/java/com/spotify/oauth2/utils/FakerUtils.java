package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
    public static String generateName(){
        Faker faker = new Faker();
        return "Playlist" + faker.random();
    }
    public static String generateDescription(){
        Faker faker = new Faker();
        return "Description" + faker.random();
    }
}
