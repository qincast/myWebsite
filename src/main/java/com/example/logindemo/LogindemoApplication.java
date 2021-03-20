package com.example.logindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class LogindemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(LogindemoApplication.class, args);
        /*
        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setPrefix("/template");
        resolver.setSuffix(".html");
     //   System.out.println("test");
     */
    }

}
