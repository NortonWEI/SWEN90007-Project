package com.freshmel.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible to get connection with MySQL database.
 * */
public class DataBaseConnection {
    private static final String DBDRIVER="com.mysql.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://47.74.65.131:3306/freshmel?autoReconnect=true";
    private static final String DBUSER="root";
    private static final String DBPASSWORD="961029";
    private Connection conn;

    public DataBaseConnection() {
        try {
            Class.forName(DBDRIVER);
            this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            System.out.println("***************database connect success!************");
        } catch (Exception e) {
            System.out.println("***************database connect failed!************");
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return this.conn ;
    }
    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close() ;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
