package com.spotify.oauth2.tests;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void beforeMethod(Method m){
        System.out.println("STARTING TEST : "  +m.getName());
        System.out.println("Thread ID : " +Thread.currentThread().getId());
    }
}
