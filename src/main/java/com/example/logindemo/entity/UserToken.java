package com.example.logindemo.entity;
@Deprecated
public class UserToken {
    private long timeStamp;
    private String userId;
    private String sign;

    public UserToken(int i, int i1, String s) {
    }

    //    private String identity;
    public String jsonifyUserToken(){
        //1.should use alibaba fastjson.
        //2.
        return "";
    }
    public static String generateSign(long timeStamp,String userid){
        //1.should return digital signature from given timestamp,userid.
        //2.better use RSA,while could use MD5 instead.[undecided]
        //<delete>3.SHOULD PERIODLICALLY CLEAR DATABASE.</delete>
        return "";
    }

}
