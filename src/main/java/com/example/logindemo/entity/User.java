package com.example.logindemo.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
//@Scope("session")
public class User {
    private long id;
    private String name;
    private String password_Hash256;
    private String gender;
    private String identity;
    @Override
    public String toString(){
        String str="id: "+id+" name :"+name+" password: "+password_Hash256+" gender: "+gender+" identity: "+identity;
        return str;
    }
    public void print(){
        System.out.println("id:"+id);
        System.out.println("name:"+name);
        System.out.println("password_hash256:"+password_Hash256);
        System.out.println("gender:"+gender);
        System.out.println("identity:"+identity);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword_Hash256() {
        return password_Hash256;
    }

    public void setPassword_Hash256(String password_Hash256) {
        this.password_Hash256 = password_Hash256;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

}
