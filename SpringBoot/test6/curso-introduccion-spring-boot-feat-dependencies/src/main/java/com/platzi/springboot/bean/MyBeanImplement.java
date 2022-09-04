package com.platzi.springboot.bean;

public class MyBeanImplement implements MyBean{
    @Override
    public void print() {
        System.out.println("tests from my own bean implementation");
    }
}
