package com.platzi.springboot.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Method: printWithDependency");
        int numero = 1;
        LOGGER.debug("number: " + numero );
        System.out.println(myOperation.sum(numero));
        System.out.println("Test 3 from a bean with dependency");
    }
}
