package com.example.demo4.Model;

import com.example.demo4.Recource.ConstARClient;
import com.example.demo4.Recource.User;

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
            String searchUser = "SELECT COUNT(*)FROM " + AUTORIZATIONS_TABLE
                    + " WHERE login = ? AND password = ?;";

            PreparedStatement prSt = getDbConnection().prepareStatement(searchUser);
            prSt.setString(1, user.getLogin());
            prSt.setBytes(2, user.getPassword());

            ResultSet autorizationDateUser = prSt.executeQuery();
            autorizationDateUser.next();
            if(autorizationDateUser.getInt("COUNT(*)") >=1 )
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

    public ResultSet getDBInfoUser() throws ClassNotFoundException,SQLException{
        ResultSet userInfo = null;
        try{
            String resTable = "Autoriz_user";
            String intermediateTable = "SELECT " + AUTORIZATIONS_TABLE + "." + AUTORIZATIONS_LOGIN + ", "
                    + AUTORIZATIONS_TABLE + "." + AUTORIZATIONS_PASSWORD + ", "
                    + USER_TABLE + "." + USER_IDPASSPORT + ", "
                    + USER_TABLE + "." + USER_NAME + ", "
                    + USER_TABLE + "." + USER_COUNTRY + ", "
                    + USER_TABLE + "." + USER_CITY + ", "
                    + USER_TABLE + "." + USER_STREET + ", "
                    + USER_TABLE + "." + USER_HOME
                    + " FROM " + AUTORIZATIONS_TABLE + " JOIN "
                    + USER_TABLE + " ON "
                    + AUTORIZATIONS_TABLE + "." + AUTORIZATIONS_IDUSER + " = "
                    + USER_TABLE + "." + USER_ID + " AS " + resTable;

            String getInfoUser = "SELECT " + resTable + "." + AUTORIZATIONS_LOGIN + ", "
                    + AUTORIZATIONS_TABLE + "." + AUTORIZATIONS_PASSWORD + ", "
                    + resTable + "." + USER_NAME + ", "
                    + resTable + "." + USER_COUNTRY + ", "
                    + resTable + "." + USER_CITY + ", "
                    + resTable + "." + USER_STREET + ", "
                    + resTable + "." + USER_HOME + ", "
                    + PASSPORT_TABLE + "." + PASSPORT_SERIES + ", "
                    + PASSPORT_TABLE + "." + PASSPORT_NUMBER
                    + " FROM " + PASSPORT_TABLE
                    + " JOIN " + intermediateTable + " ON "
                    + resTable + "." + USER_IDPASSPORT + " = "
                    + PASSPORT_TABLE + "." + PASSPORT_ID;

            PreparedStatement prSt = getDbConnection().prepareStatement(getInfoUser);

            userInfo = prSt.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
