package com.example.qltc.database;

import com.example.qltc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseResult {
    public User getUser(){
        Connection con;
        try{
            con = new DatabaseConnection().connection();
            String sql = "SELECT * FROM user";
            User a = new User();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                a = new User(resultSet.getString(1),resultSet.getString(2),resultSet.getLong(3));
            }
            con.close();
            return a;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        User a = new DatabaseResult().getUser();
        System.out.println(a);
    }
}
