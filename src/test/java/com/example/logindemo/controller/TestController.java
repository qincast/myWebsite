package com.example.logindemo.controller;

import com.example.logindemo.DAO.MysqlConnection;
import com.example.logindemo.DAO.UserInfoDAO;
import com.example.logindemo.entity.TestEntity;
import com.example.logindemo.entity.User;
import com.example.logindemo.util.RSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
public class TestController {
    @Autowired
    UserInfoDAO userInfoDAO;
    @Autowired
    TestEntity testEntity;
    @Autowired
    MysqlConnection mysqlConnection;
    @RequestMapping("/test")
    public ModelAndView test( HttpServletRequest request) throws SQLException {
        //model.addAttribute("message","this is a message from backend to thymeleaf");

        ModelAndView mav=new ModelAndView();
        System.out.println(testEntity.getMessage());
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("test","this is hashmaptext");
        mav.addObject("message","message from add object");
        mav.addObject("map",hashMap);
        mav.getModel().put("name","message2_");
        mav.addObject("isLogin",true);
        User user =userInfoDAO.getUserByName("error");

        user.print();
        return mav;
    }
    public void mysqlTest() throws SQLException {
        ResultSet rs;
        String SQL="SELECT * FROM userdemo";
        rs=mysqlConnection.excuteQuery(SQL);
        while(rs.next()) {
            System.out.println(rs.getInt("id"));
        }
    }
    public void ymlTest(){
        System.out.println("YMLMESSAGE:"+testEntity.getYmlMessage());
    }
    public void RSATest(){
        try {
            String message = "This is RSATEST ";
            String encrypted = RSA.encrypt(message);
            System.out.println("Encrypted:" + encrypted);
            String decrypted = RSA.decrypt(encrypted);
            System.out.println("Decrypted" + decrypted);
        }
        catch (Exception e){e.printStackTrace();}
    }

}
