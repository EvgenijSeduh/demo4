package com.example.demo4.Model;

import com.example.demo4.Recource.Const.ConstAllTable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class AdminPanelModel extends ConstAllTable {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;


        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
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
            PreparedStatement prSt = getDbConnection().prepareStatement(insertBike);
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

            PreparedStatement prSt = getDbConnection().prepareStatement(allShops);

            return result = prSt.executeQuery();


        } catch (SQLException | ClassNotFoundException e) {
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
        PreparedStatement prSt = getDbConnection().prepareStatement(getInfoBusyBike);
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

        PreparedStatement prSt = getDbConnection().prepareStatement(getInfoFreeBike);
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

            PreparedStatement prSt = getDbConnection().prepareStatement(getInfoUser);
            result = prSt.executeQuery();
        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            errorAlert();
            e.printStackTrace();
        }
        return result;
    }

}
