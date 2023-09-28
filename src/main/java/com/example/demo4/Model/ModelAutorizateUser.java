package com.example.demo4.Model;

import com.example.demo4.Recource.Const.ConstAllTable;
import com.example.demo4.Recource.InfoUser;

import java.sql.*;

public class ModelAutorizateUser extends ConstAllTable {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;


        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }


    public boolean checkUser(InfoUser infoUser) throws SQLException, ClassNotFoundException {
        try {
            String searchUser = "SELECT COUNT(*)FROM " + AUTORIZATIONS_TABLE
                    + " WHERE login = ? AND password = ?;";

            PreparedStatement prSt = getDbConnection().prepareStatement(searchUser);
            prSt.setString(1, infoUser.getLogin());
            prSt.setBytes(2, infoUser.getPassword());

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

    public String checkMandat(InfoUser infoUser) throws SQLException, ClassNotFoundException {
        ResultSet result = null;
        try {
            String getMandatString = "SELECT " + USER_MANDAT + ", "
                    + AUTORIZATIONS_LOGIN + ", "
                    + AUTORIZATIONS_PASSWORD + " FROM "
                    + AUTORIZATIONS_TABLE + " JOIN " + USER_TABLE
                    + " ON " + AUTORIZATIONS_TABLE +"." + AUTORIZATIONS_IDUSER + " = "
                    +USER_TABLE + "." + USER_ID + " WHERE "
                    + AUTORIZATIONS_TABLE + "." + AUTORIZATIONS_LOGIN + " = ?"
                    + " AND " + AUTORIZATIONS_TABLE + "." + AUTORIZATIONS_PASSWORD + " = ?";
            System.out.println(getMandatString);
            PreparedStatement prSt = getDbConnection().prepareStatement(getMandatString);
            prSt.setString(1, infoUser.getLogin());
            prSt.setBytes(2, infoUser.getPassword());

            result = prSt.executeQuery();
            result.next();
            System.out.println(result.getString(USER_MANDAT));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result.getString(USER_MANDAT);

    }
}
