package com.example.logindemo.DAO;

import com.alibaba.fastjson.JSON;
import com.example.logindemo.entity.User;
import com.example.logindemo.entity.UserCookie;
import com.example.logindemo.util.RSA;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    public static void generateUserToken(UserCookie userCookie){
        try {
            String token = RSA.encrypt(userCookie.toString());
            userCookie.setToken(token);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static boolean validCookie(String cookiesStr){
        UserCookie userCookie= JSON.parseObject(cookiesStr,UserCookie.class);
        try {

            String token= RSA.encrypt(userCookie.toString());
            /*
            System.out.println("Cookie: "+userCookie.toString());
            System.out.println("Expect TOKEN:"+token);
            System.out.println("Receive Token:"+userCookie.getToken());
            System.out.println("Decode Received Token:"+RSA.decrypt(userCookie.getToken()));
            System.out.println("Decode Expect Token:"+RSA.decrypt(token));
            */
            String decryptStr=RSA.decrypt(userCookie.getToken());
            if(decryptStr.equals(userCookie.toString())){
                return true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }



        return false;
    }
}
