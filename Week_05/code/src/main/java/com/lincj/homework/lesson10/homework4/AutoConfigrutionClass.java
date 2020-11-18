package com.lincj.homework.lesson10.homework4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AutoConfigruationProperties.class)
@ConditionalOnClass(Student.class)
public class AutoConfigrutionClass {

    @Autowired
    private AutoConfigruationProperties autoConfigruationProperties;

    @ConditionalOnMissingBean
    @Bean
    public Student getHashCodeClass(){
        return new Student(autoConfigruationProperties.getId(), autoConfigruationProperties.getName(),
                autoConfigruationProperties.getNumber());
    }
}
