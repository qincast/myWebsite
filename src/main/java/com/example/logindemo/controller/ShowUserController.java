package com.example.logindemo.controller;

import com.example.logindemo.DAO.UserInfoDAO;
import com.example.logindemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ShowUserController {
    @Autowired
    UserInfoDAO userInfoDAO;
@RequestMapping("/showuser")
    public ModelAndView showUserController(HttpServletRequest request){
    boolean isLogin=false;
    HttpSession session=request.getSession();
    ModelAndView mav=new ModelAndView("showuser");
    if(session.getAttribute("isLogin")!=null)
    isLogin=(boolean)session.getAttribute("isLogin");
    if(isLogin){
        User user=(User)session.getAttribute("user");
        if(user.getIdentity().equals("admin")){
            /*
            *
            *
            *
            * */
            mav.addObject("message","admin priviledge");
            //System.out.println("admin priviledge");
            ArrayList userList=userInfoDAO.getAllUsers();
            //while(userList.iterator())
            mav.addObject("userList",userList);
        }
        else{
            mav.addObject("message","not enough priviledge");
        }

    }
    else{
        mav.addObject("message","please log in");
    }
    return mav;

}
}
