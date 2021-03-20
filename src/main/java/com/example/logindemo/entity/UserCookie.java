package com.example.logindemo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.sql.Timestamp;

public class UserCookie {


    @JSONField(name="id")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JSONField(name="timeStamp")
    private Timestamp timestamp;
    @JSONField(name="token")
    private String token;
    public UserCookie(){

    }
    public String toString(){
        return "id:"+id+"timestamp:"+timestamp;
    }
    public UserCookie(long id) {
        this.id = id;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
    public UserCookie(long id, Timestamp timestamp, String token) {
        this.id = id;
        this.timestamp = timestamp;
        this.token = token;
    }
}
