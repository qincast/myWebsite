package com.example.logindemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.logindemo.DAO.UserInfoDAO;
import com.example.logindemo.DAO.UserInfoService;
import com.example.logindemo.entity.User;
import com.example.logindemo.entity.UserCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
public class ValidateUserController {
    @Autowired
    UserInfoDAO userInfoDAO;

    @RequestMapping("/validate")
    public String validate(HttpServletRequest httpServletRequest, HttpServletResponse response){
        User user = null;
        HttpSession session= httpServletRequest.getSession();
        boolean isValidated=false;//TODO this should be done. default FALSE
        String username=httpServletRequest.getParameter("username");
        String password=httpServletRequest.getParameter("password");
       // System.out.println("username: "+username+" password :"+password);
        if(username!=null){
            user=userInfoDAO.getUserByName(username);
//            user.print();
            if(user!=null) {

                if (user.getPassword_Hash256().equals(password)&&user.getPassword_Hash256()!=null) {//TODO encrypt this.
                    isValidated = true;
                    session.setAttribute("isLogin",true);
                    session.setAttribute("user",user);
                }
            }
        }
        if(isValidated){
            //TODO give cookies
            UserCookie userCookie=new UserCookie(user.getId());
            UserInfoService.generateUserToken(userCookie);
            try{
                String jsonfiedUserCookie=JSON.toJSONString(userCookie);
                String encodedUserCookies = URLEncoder.encode(jsonfiedUserCookie, "utf-8");
                Cookie tokenCookie = new Cookie("token", encodedUserCookies);// TODO should be a json string/
                tokenCookie.setMaxAge(30 * 24 * 60 * 60);//30 Days life time.
                response.addCookie(tokenCookie);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return "forward:/index";
        }
        else{
            String message="Username&Password mismatch.";
            httpServletRequest.setAttribute("message",message);
            return "forward:/login";
        }

    }
}
