package com.example.logindemo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "testentity" )
public class TestEntity {
    private String  message="this is message from testEntity";
    private String ymlMessage;
    public TestEntity(){
        System.out.println("LOAD OF TEST ENTITY");
        System.out.println("LOAD: "+ymlMessage);
    }
    public void setYmlMessage(String ymlMessage){
        this.ymlMessage=ymlMessage;

    }
    public String getYmlMessage(){
        return ymlMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
