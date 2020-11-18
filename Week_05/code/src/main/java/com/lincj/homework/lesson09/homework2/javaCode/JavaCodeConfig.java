package com.lincj.homework.lesson09.homework2.javaCode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaCodeConfig {

    @Bean(name = "javaCodeBean")
    public JavaCodeBean getJavaCodeBean() {
        return new JavaCodeBean();
    }

}
