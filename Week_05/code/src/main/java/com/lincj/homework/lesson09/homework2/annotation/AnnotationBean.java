package com.lincj.homework.lesson09.homework2.annotation;

import org.springframework.beans.factory.annotation.Autowired;

public class AnnotationBean {

    @Autowired
    private TestBean testBean;

    public void printInfo() {
        System.out.println("这是通过" + testBean.getType() + "方式装配的Bean");
    }
}
