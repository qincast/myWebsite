package com.example.logindemo.DAO;

import com.example.logindemo.entity.User;

import java.util.ArrayList;

public interface UserInfoDAOInterface {
    //String db_url = null;
    public User getUserByName(String userName);
    public User getUserById(String id);
    public boolean updateUser(User user);//TODO
    public boolean insertUser(User user);//TODO
    public ArrayList<User> getAllUsers();
}
//password:A523as3AKLH@#