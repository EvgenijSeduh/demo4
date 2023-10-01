package com.example.demo4.Recource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHandler {
    protected final String dbHost = "std-mysql.ist.mospolytech.ru";
    protected final String dbPort = "3306";
    protected final String dbUser = "std_2278_bike";
    protected final String dbPass = "qwertyui";
    protected final String dbName = "std_2278_bike";

    private static DataBaseHandler instracte = null;
    private Connection connection;

    public DataBaseHandler() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(connectionString, dbUser, dbPass);

    }
    public static DataBaseHandler getInstance() throws ClassNotFoundException, SQLException {
        if(instracte == null)
            instracte = new DataBaseHandler();
        return instracte;
    }
    public Connection getConnection(){return connection;}
}
