package com.platzi.springboot.component;


import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements  ComponentDependency{

    @Override
    public void saludar() {
        System.out.println("test 2");
    }
}
