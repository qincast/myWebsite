package com.example.logindemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.logindemo.DAO.UserInfoDAO;
import com.example.logindemo.DAO.UserInfoService;
import com.example.logindemo.entity.User;
import com.example.logindemo.entity.UserCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;


//1.provide login function.
//2.should encrypt password in transmission[HTTPS or something else].X
//3.should store password in hash to prevent database leak.At least not in plain text.
//4.should use cookies for validation.
//5.should use RSA algorithm to prevent cookies from fabrication.
//6.should use TimeStamp for cookies expiration.
//7.should check user input [1.too long 2. SQL insertion 3.].
//8.should limit user input times to prevent malicious guess.[base on session and IP].
//should use POST instead GET.
//

/*Under consideration:
* 1.localization
* 2.AJAX
*
* */
@Controller
public class LoginPageController {

    @Autowired
    UserInfoDAO userInfoDAO;


    @RequestMapping({"/index","/"})
    public ModelAndView login(HttpServletRequest request){
        ModelAndView mav =new ModelAndView();
        mav.setViewName("index");
        Boolean isLogin=false;
        HttpSession sessoin=request.getSession();
        User user=(User)sessoin.getAttribute("user");
        Cookie cookies[]=request.getCookies();
        if (null!=cookies) {
        //have cookie
            for(Cookie cookie : cookies){
                System.out.println("name:"+cookie.getName()+",value:"+ cookie.getValue());//TODO delete this line
                if(cookie.getName().equals("token")&&cookie.getValue()!=null){
                    try {
                        String decodedUserCookies = URLDecoder.decode(cookie.getValue(), "utf-8");
                        // String encodedUserCookies = URLEncoder.encode;
                       // cookie.getValue();
                        if (UserInfoService.validCookie(decodedUserCookies)) {
                            UserCookie userCookie = JSON.parseObject(decodedUserCookies, UserCookie.class);
                            //validated cookies and login
                            user = userInfoDAO.getUserById(userCookie.getId() + "");
                            sessoin.setAttribute("user", user);
                            sessoin.setAttribute("isLogin",true);
                            isLogin=true;
                            System.out.println("Cookies validated");
                            //user.print();
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        if (user != null) {
            isLogin=(Boolean) sessoin.getAttribute("isLogin");
            if(isLogin!=null){
                mav.addObject("isLogin",isLogin);
            }
            mav.addObject("userName",user.getName());
            user.print();
            //mav.addObject("isLogin",true);
        }

        return mav;
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        session.removeAttribute("isLogin");
        session.setAttribute("isLogin",false);
        Cookie tokenCookie=new Cookie("token","");
        response.addCookie(tokenCookie);
        return "redirect:/index";
    }
    @RequestMapping("/login")
    public ModelAndView loginForward(HttpServletRequest request){
        HttpSession session=request.getSession();

        ModelAndView mav=new ModelAndView("login");
        String message=(String) request.getAttribute("message");
        if(message==null){
            mav.addObject("message","please input your username & password.");
        }
        else{
            mav.addObject("message",message);
        }
        //check the given parameter
        //connect to the database and query the related information.
        //
        return mav;
    }


}
