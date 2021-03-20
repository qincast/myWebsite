package com.example.logindemo.DAO;

import com.example.logindemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Scope("singleton")
@Repository
@ConfigurationProperties(prefix= "user")
public class UserInfoDAO implements UserInfoDAOInterface {
    @Autowired
    MysqlConnection mysqlConnection;

    public Map getGenderMap() {
        return genderMap;
    }

    public void setGenderMap(Map genderMap) {
        this.genderMap = genderMap;
    }
    private Map genderMap;
    public  UserInfoDAO(){
        genderMap=new HashMap();
        genderMap.put(0,"undefined");
        genderMap.put(1,"male");
        genderMap.put(2,"female");

    }
    public ArrayList<User> convertResultSetToUserList(ResultSet rs){
        User user =new User();
        ArrayList<User> userList=new ArrayList<>();
        try {
            while(rs.next()){
                user =new User();
                user.setId(rs.getInt("id"));
                user.setIdentity(rs.getString("identity"));
                user.setName(rs.getString("name"));
                user.setPassword_Hash256(rs.getString("password_hash256"));
                String gender=(String)genderMap.get(rs.getInt("gender"));
                if(gender==null){
                    gender="error";
                }
                else{
                    user.setGender(gender);
                }
                //user.getGender(rs.)
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public User convertResultSetToUser(ResultSet rs){
        User user =new User();
        try {
            if(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setIdentity(rs.getString("identity"));
                user.setName(rs.getString("name"));
                user.setPassword_Hash256(rs.getString("password_hash256"));
                //should be delete
                String gender=(String)genderMap.get(rs.getInt("gender"));
                if(gender==null){
                    gender="error";//TODO why ????
                }
                else{
                    user.setGender(gender);
                }
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public User getUserByName(String userName) {
        String SQL="SELECT * FROM userdemo WHERE name = \""
                +userName
                +"\"";
        ResultSet rs= mysqlConnection.excuteQuery(SQL);
        return convertResultSetToUser(rs);
        //System.out.println(SQL);

    }

    @Override
    public User getUserById(String id) {
        String SQL="SELECT * FROM userdemo WHERE id = \""
                +id
                +"\"";
        ResultSet rs= mysqlConnection.excuteQuery(SQL);
        return convertResultSetToUser(rs);
    }

    @Override
    public boolean updateUser(User user) {

        return false;
    }

    @Override
    public boolean insertUser(User user) {

        return false;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        String SQL="SELECT * FROM userdemo ";
        ResultSet rs= mysqlConnection.excuteQuery(SQL);
        return convertResultSetToUserList(rs);
    }
}
