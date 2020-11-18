package com.lincj.homework.lesson09.homework2;

import com.lincj.homework.lesson09.homework2.annotation.AnnotationBean;
import com.lincj.homework.lesson09.homework2.javaCode.JavaCodeBean;
import com.lincj.homework.lesson09.homework2.javaCode.JavaCodeConfig;
import com.lincj.homework.lesson09.homework2.xml.XmlBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Homework2Application {

    public static void main(String[] args) {
        //通过xml方式装配Bean
        /*ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-xml-bean.xml");
        XmlBean xmlBean = context.getBean("xmlBean", XmlBean.class);
        xmlBean.printInfo();*/

        //通过Java配置类装配Bean
        /*ApplicationContext context = new AnnotationConfigApplicationContext(JavaCodeConfig.class);
        JavaCodeBean javaCodeBean = context.getBean("javaCodeBean", JavaCodeBean.class);
        javaCodeBean.printInfo();*/

        //通过annotation方式装配Bean
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-annotation-bean.xml");
        AnnotationBean annotationBean = context.getBean("annotationBean", AnnotationBean.class);
        annotationBean.printInfo();
    }
}
