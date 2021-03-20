package com.example.logindemo.DAO;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

//@Scope("Singleton")
@Component
@ConfigurationProperties(prefix= "database")
public class MysqlConnection {
    private boolean isAlive=false;
    private static String db_url;
    private static String userName;
    private static String password;
    private static String JDBC_DRIVER;
    private static Connection conn = null;
    private static Statement stmt = null;
    public static String getJDBC_Driver() {
        return JDBC_DRIVER;
    }
    public static void setJDBC_Driver(String JDBC_Driver) {
        MysqlConnection.JDBC_DRIVER = JDBC_Driver;
    }
    public String getDb_url() {
        return db_url;
    }
    public void setDb_url(String db_url) {
        this.db_url = db_url;
    }
    //1.provide mysql connection.
    //2.use mutex lock to ensure data integrity.[use singleton]
    //3.for given sql ,return corresponding result.
    //4.handle exception[important].
    //5.disinfect sql to prevent sql insertion.
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public MysqlConnection(){

    }
    public boolean connect(){
        try {
            //Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to the MYSQL database..");
            conn = DriverManager.getConnection(db_url, userName, password);
            System.out.println("initializing statement object");
            stmt = conn.createStatement();
            System.out.println("MySQL connection OK");
            isAlive=true;
        }
        catch (Exception e){
            e.printStackTrace();
            isAlive=false;
            return false;
        }
        return true;
    }
    public ResultSet excuteQuery(String sql){
        if(!isAlive){
            connect();
        }
        try {
            return stmt.executeQuery(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
