package com.eduit.javaseweb.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionManager {

    private String driver = "";
    private String url = "";
    private String user = "";
    private String password = "";
    private static DataSource ds;
    private static ConnectionManager connection;

    private ConnectionManager(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }
    
    
    public static synchronized ConnectionManager getConnectionManager(String driver, String url, String user, String password){
        if(connection == null){
            connection = new ConnectionManager(driver, url, user, password);
        }
        
        return connection;
    }
    
    public Connection getConnection() throws SQLException{
        if(ds == null){
            loadBasicDataSource();
        }
        
        return ds.getConnection();
    }
    
    
    private void loadBasicDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        basicDataSource.setInitialSize(5);
        
        ds = basicDataSource;
    }
    
    
}
