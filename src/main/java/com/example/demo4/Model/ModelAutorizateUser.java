package com.example.demo4.Model;

import com.example.demo4.Recource.Const.ConstAllTable;
import com.example.demo4.Recource.DataBaseHandler;
import com.example.demo4.Recource.InfoUser;

import java.sql.*;

public class ModelAutorizateUser extends ConstAllTable {
    private static int idAutorizate;
    private static int idUser;
    DataBaseHandler dataBaseHandler = DataBaseHandler.getInstance();

    public ModelAutorizateUser() throws SQLException, ClassNotFoundException {
    }

    public static int getIdAutorizate() {
        return idAutorizate;
    }

    public static void setIdAutorizate(int idAutorizate) {
        ModelAutorizateUser.idAutorizate = idAutorizate;
    }

    public static int getIdUser() {
        return idUser;
    }

    public static void setIdUser(int idUser) {
        ModelAutorizateUser.idUser = idUser;
    }




    public boolean checkUser(InfoUser infoUser) throws SQLException, ClassNotFoundException {
        try {
            String searchUser = "SELECT " + AUTORIZATIONS_ID + " FROM " + AUTORIZATIONS_TABLE
                    + " WHERE login = ? AND password = ?;";

            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(searchUser);
            prSt.setString(1, infoUser.getLogin());
            prSt.setBytes(2, infoUser.getPassword());

            ResultSet autorizationDateUser = prSt.executeQuery();
            if(autorizationDateUser.next()) {
                setIdAutorizate(autorizationDateUser.getInt("id"));
                return true;
            }else
                return false;
        } catch (SQLException e) {
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

            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(getInfoUser);

            userInfo = prSt.executeQuery();
        }
        catch (SQLException e) {
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
            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(getMandatString);
            prSt.setString(1, infoUser.getLogin());
            prSt.setBytes(2, infoUser.getPassword());

            result = prSt.executeQuery();
            result.next();
            System.out.println(result.getString(USER_MANDAT));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result.getString(USER_MANDAT);

    }
}
