package com.eduit.javaseweb.integrator.dao;

import com.eduit.javaseweb.utils.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Dao {
    public static synchronized Connection getConnection() throws SQLException{
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/javaseweb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        final String user = "root";
        final String password = "admin";
        
        return ConnectionManager.getConnectionManager(driver, url, user, password).getConnection();                
    }            
}