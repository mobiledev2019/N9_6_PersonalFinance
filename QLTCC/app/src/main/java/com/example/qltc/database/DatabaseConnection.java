package com.example.qltc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qltc","root","");
        if(connection != null){
            System.out.println("Success-connection");
        }else{
            System.out.println("Fail-connection");
        }
        return connection;
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        connection();
//    }

}
