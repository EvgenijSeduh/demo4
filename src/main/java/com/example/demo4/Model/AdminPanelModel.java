package com.example.demo4.Model;

import com.example.demo4.Controler.EntryController;
import com.example.demo4.Recource.Bicycle;
import com.example.demo4.Recource.Const.ConstAllTable;
import com.example.demo4.Recource.DataBaseHandler;
import com.example.demo4.Recource.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class AdminPanelModel extends ConstAllTable {
    DataBaseHandler dataBaseHandler = DataBaseHandler.getInstance();
    public AdminPanelModel() throws SQLException, ClassNotFoundException {
    }

    public boolean addBike(String name, String type, String model, String numbergear, String price, int idshop) {
        try {
            String insertBike = "INSERT INTO " + BIKE_TABLE + "("
                    + BIKE_IDSHOS + ", "
                    + BIKE_NAME + ", "
                    + BIKE_MODEL + ", "
                    + BIKE_TYPE + ", "
                    + BIKE_NUMBERGEAR + ", "
                    + BIKE_STATUS + ", "
                    + BIKE_PRICEHOUR + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(insertBike);
            prSt.setInt(1, idshop);
            prSt.setString(2, name);
            prSt.setString(3, model);
            prSt.setString(4, type);
            prSt.setInt(5, Integer.parseInt(numbergear));
            prSt.setString(6, "free");
            prSt.setInt(7, Integer.parseInt(price));

            System.out.println(prSt.toString());

            prSt.executeUpdate();
        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void errorAlert() {
        Alert errorBD = new Alert(Alert.AlertType.ERROR, "Ошибка при работе с базой данных", ButtonType.OK);
        errorBD.showAndWait();
    }

    public ResultSet getAllShops() {
        ResultSet result = null;
        try {
            String allShops = "SELECT * FROM " + SHOPS_TABLE;

            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(allShops);

            return result = prSt.executeQuery();


        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
        }
        return result;
    }
    public ResultSet getBusyBikeInfo() throws SQLException, ClassNotFoundException {
        String nameIntermediateTable = "bike_data";

        String getInfoBusyBike = "SELECT "
                + nameIntermediateTable + "." + BIKE_ID + ", "
                + nameIntermediateTable + "." + BIKE_NAME + ", "
                + nameIntermediateTable + "." + BIKE_MODEL + ", "
                + nameIntermediateTable + "." + BIKE_TYPE + ", "
                + nameIntermediateTable + "." + BIKE_NUMBERGEAR + ", "
                + nameIntermediateTable + "." + BIKE_STATUS + ", "
                + nameIntermediateTable + "." + BIKE_PRICEHOUR
                + ", MAX(" + RESERVATION_TABLE + "." + RESERVATION_DATERECEIPT
                + ") FROM (SELECT * FROM "
                + BIKE_TABLE + " WHERE " + BIKE_TABLE + "." + BIKE_STATUS + " = ?) AS " + nameIntermediateTable
                + " JOIN " + RESERVATION_TABLE + " ON " + nameIntermediateTable + "." + BIKE_ID
                + " = " + RESERVATION_TABLE + "." + RESERVATION_IDBIKES
                + " GROUP BY " + nameIntermediateTable + "." + BIKE_ID + ";";


        System.out.println(getInfoBusyBike);
        PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(getInfoBusyBike);
        prSt.setString(1,"busy");
        return prSt.executeQuery();
    }

    public ResultSet getFreeBikeInfo() throws SQLException, ClassNotFoundException {
        String getInfoFreeBike = "SELECT "
                + BIKE_TABLE + "." + BIKE_ID + ", "
                + BIKE_TABLE + "." + BIKE_NAME + ", "
                + BIKE_TABLE + "." + BIKE_MODEL + ", "
                + BIKE_TABLE + "." + BIKE_TYPE + ", "
                + BIKE_TABLE + "." + BIKE_NUMBERGEAR + ", "
                + BIKE_TABLE + "." + BIKE_STATUS + ", "
                + BIKE_TABLE + "." + BIKE_PRICEHOUR + ", "
                + SHOPS_TABLE + "." + SHOPS_ADDRESS
                + " FROM " + BIKE_TABLE + " JOIN " + SHOPS_TABLE
                + " ON " + BIKE_TABLE + "." + BIKE_IDSHOS + " = "
                + SHOPS_TABLE + "." + SHOPS_ID
                + " WHERE " + BIKE_TABLE + "." + BIKE_STATUS + " = ?;";

        PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(getInfoFreeBike);
        prSt.setString(1, "free");
        return prSt.executeQuery();
    }

    public ResultSet getUserInfo() throws SQLException, ClassNotFoundException {
        ResultSet result = null;
        try {
            String getInfoUser = "SELECT "
                    + USER_TABLE + "." + USER_ID + ", "
                    + USER_TABLE + "." + USER_NAME + ", "
                    + PASSPORT_TABLE + "." + PASSPORT_SERIES + ", "
                    + PASSPORT_TABLE + "." + PASSPORT_NUMBER + ", "
                    + USER_TABLE + "." + USER_COUNTRY + ", "
                    + USER_TABLE + "." + USER_CITY + ", "
                    + USER_TABLE + "." + USER_STREET + ", "
                    + USER_TABLE + "." + USER_HOME + ", "
                    + USER_TABLE + "." + USER_MANDAT
                    + " FROM " + USER_TABLE + " JOIN "
                    + PASSPORT_TABLE + " ON "
                    + USER_TABLE + "." + USER_IDPASSPORT + " = "
                    + PASSPORT_TABLE + "." + PASSPORT_ID;
            System.out.println(getInfoUser);

            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(getInfoUser);
            result = prSt.executeQuery();
        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
        }
        return result;
    }

    public void raiseStatusUser(String status, int idUser){
        try {
            if (status.equals("client"))
                status = "employee";
            else if (status.equals("employee"))
                status = "super_user";
            else if (status.equals("super_user"))
                status = "client";

            String raiseStatusUserSQL = "UPDATE " + USER_TABLE + " SET "
                    + USER_MANDAT + " = ? WHERE " + USER_ID + " = ?;";
            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(raiseStatusUserSQL);
            prSt.setString(1, status);
            prSt.setInt(2, idUser);

            prSt.executeUpdate();
        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
        }
    }

    public boolean deleteUser(User user) {
        try{
            String deleteAutorizateSQL = "DELETE FROM " + AUTORIZATIONS_TABLE
                    + " WHERE " + AUTORIZATIONS_IDUSER + " = ?";

            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(deleteAutorizateSQL);
            prSt.setInt(1, user.getId());

            prSt.executeUpdate();

            String deleteUserSQL = "DELETE FROM " + USER_TABLE
                    + " WHERE " + USER_ID + " = ?";

            prSt = dataBaseHandler.getConnection().prepareStatement(deleteUserSQL);
            prSt.setInt(1, user.getId());

            prSt.executeUpdate();

            String deletePassportSQL = "DELETE FROM " + PASSPORT_TABLE
                    + " WHERE " + PASSPORT_ID + " = (SELECT " + USER_IDPASSPORT + " FROM " + USER_TABLE + " WHERE " + USER_ID + " = ?" + ")";

            prSt = dataBaseHandler.getConnection().prepareStatement(deletePassportSQL);
            prSt.setInt(1, user.getId());

            prSt.executeUpdate();

        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteBike(Bicycle bike) {
        try{
            String deleteUserSQL = "DELETE FROM " + BIKE_TABLE
                    + " WHERE " + BIKE_ID + " = ?;";

            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(deleteUserSQL);
            prSt.setInt(1, bike.getId());

            prSt.executeQuery();

        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
