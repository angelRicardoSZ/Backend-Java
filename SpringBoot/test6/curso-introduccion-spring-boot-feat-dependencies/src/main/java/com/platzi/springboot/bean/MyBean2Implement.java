package com.platzi.springboot.bean;

public class MyBean2Implement implements MyBean{
    @Override
    public void print() {
        System.out.println("test 2 from my own bean implementation");
    }
}
