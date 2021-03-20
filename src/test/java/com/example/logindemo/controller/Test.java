package com.example.logindemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.logindemo.DAO.UserInfoService;
import com.example.logindemo.entity.UserCookie;
import com.example.logindemo.entity.UserToken;
import com.example.logindemo.util.Hash;
import com.example.logindemo.util.RSA;

import java.sql.Timestamp;
import java.util.Arrays;

public class Test {
    public static void main(String args[]) throws Exception {
     //   byte[] bytes=Hash.SHA("12345");
      //  System.out.println(bytes[0]);
       // System.out.println(bytes);
        //System.out.println(Hash.SHA("str"));'
  //
        int a[][]=new int[10][120];
        System.out.println(a.length);
        System.out.println(a[1].length);
        //int a=1;
        int b=5;
        int c=9;
        int d=99;
       // a*=10;
       // a+=10;
      //  System.out.println(a/10);
        System.out.println(b/10);
        System.out.println(c/10);
        System.out.println(d/10);
    }

    private static String getString(byte[] bytes) {
        return Arrays.toString(bytes);
    }
}
