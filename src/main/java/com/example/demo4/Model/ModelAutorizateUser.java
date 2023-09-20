package com.example.demo4.Model;

import com.example.demo4.User;

import java.sql.*;

public class ModelAutorizateUser extends ConstARClient {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;


        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }


    public boolean checkUser(User user) throws SQLException, ClassNotFoundException {
        try {
            String searchUser = "SELECT * FROM " + AUTORIZATIONS_TABLE
                    + " WHERE login = ? AND password = ?;";

            PreparedStatement prSt = getDbConnection().prepareStatement(searchUser);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            ResultSet autorizationDateUser = prSt.executeQuery();
            if(autorizationDateUser.next())
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
