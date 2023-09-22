package com.example.demo4.Model;

import com.example.demo4.Recource.ConstARClient;
import com.example.demo4.Recource.ConstClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PanelClientModel extends ConstClient {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;


        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public void getBikeInfo() {
        String getBukeInfo = "SELECT * FR"

    }


    //dbConnection.close();
}
