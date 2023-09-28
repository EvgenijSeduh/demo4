package com.example.demo4.Model;

import com.example.demo4.Recource.Bicycle;
import com.example.demo4.Recource.Const.ConstAllTable;
import com.example.demo4.Recource.Shop;
import com.example.demo4.Recource.ShortUser;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.sql.*;
import java.time.LocalDate;


public class ModelEmployeePanel extends ConstAllTable {
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;


        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public ResultSet getBusyBikeInfo() throws SQLException, ClassNotFoundException {
        ResultSet result = null;
        try {
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
            prSt.setString(1, "busy");
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

    public ResultSet getFreeBikeInfo() throws SQLException, ClassNotFoundException {
        ResultSet result = null;
        try {
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

    public ResultSet getShortUserInfo() throws SQLException, ClassNotFoundException {
        ResultSet result = null;
        try {
            String getInfoUser = "SELECT "
                    + USER_TABLE + "." + USER_ID + ", "
                    + USER_TABLE + "." + USER_NAME + ", "
                    + PASSPORT_TABLE + "." + PASSPORT_SERIES + ", "
                    + PASSPORT_TABLE + "." + PASSPORT_NUMBER
                    + " FROM " + USER_TABLE + " JOIN "
                    + PASSPORT_TABLE + " ON "
                    + USER_TABLE + "." + USER_IDPASSPORT + " = "
                    + PASSPORT_TABLE + "." + PASSPORT_ID + " WHERE "
                    + USER_MANDAT + " = ?";
            System.out.println(getInfoUser);

            PreparedStatement prSt = getDbConnection().prepareStatement(getInfoUser);
            prSt.setString(1, "client");
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

    public boolean createCloseRent(ShortUser user, Bicycle bike, Shop shop) {
        try {
            String createCloseRent = "INSERT INTO " + RANTING_TABLE + " ("
                    + RANTING_IDBIKES + ", " + RANTING_IDSHOPS + ", " + RANTING_DATEDELIVERY
                    + ") VALUES ( ?, ?, ?);";
            String createConnectionWithReservationAndRanting = "INSERT INTO "
                    + RENTS_TABLE + " (" + RENTS_IDRATINGS + ", " + RENTS_IDRESERVATION
                    + ") VALUES (?, ?)";
            String changeStatusAndAddressBike = "UPDATE " + BIKE_TABLE + " SET "
                    + BIKE_STATUS + " = ?," + BIKE_IDSHOS + " = ?" + " WHERE " + BIKE_ID + " = ?;";

            PreparedStatement prSt = getDbConnection().prepareStatement(createCloseRent, Statement.RETURN_GENERATED_KEYS);
            prSt.setInt(1, bike.getId());
            prSt.setInt(2, shop.getId());
            prSt.setDate(3, Date.valueOf(LocalDate.now()));

            System.out.println(prSt.toString());

            prSt.executeUpdate();
            ResultSet idRanting = prSt.getGeneratedKeys();
            idRanting.next();
            System.out.println(idRanting.getMetaData());

            prSt = getDbConnection().prepareStatement(createConnectionWithReservationAndRanting);
            prSt.setInt(1, idRanting.getInt(1));
            prSt.setInt(2, getReservation(user, bike, shop).getInt(RESERVATION_ID));

            prSt.executeUpdate();

            prSt = getDbConnection().prepareStatement(changeStatusAndAddressBike);
            prSt.setString(1, "free");
            prSt.setInt(2,shop.getId());
            prSt.setInt(3, bike.getId());

            prSt.executeUpdate();

        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            errorAlert();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ResultSet getReservation(ShortUser user, Bicycle bike,Shop shop) throws SQLException {
        ResultSet result = null;
        try{
            String getIdReservation = "SELECT " + RESERVATION_TABLE + "." +RESERVATION_ID
                    + " FROM " + RESERVATION_TABLE
                    + " JOIN " + SHOPS_TABLE + " ON "
                    + RESERVATION_TABLE + "." +RESERVATION_IDSHOPS + " = "
                    + SHOPS_TABLE + "." + SHOPS_ID
                    + " WHERE "
                    + SHOPS_TABLE + "." + SHOPS_ID + " = (SELECT " + BIKE_IDSHOS + " FROM " + BIKE_TABLE
                    + " WHERE " + BIKE_ID + " = ?" + ") AND "
                    + RESERVATION_TABLE + "." + RESERVATION_IDBIKES + " = ? AND "
                    + RESERVATION_TABLE + "." + RESERVATION_IDUSER + " = ? AND "
                    + RESERVATION_TABLE + "." + RESERVATION_DATERECEIPT + " = (STR_TO_DATE(?, '%Y-%m-%d'))";
            PreparedStatement prSt = getDbConnection().prepareStatement(getIdReservation);
            prSt.setInt(1, bike.getId());
            prSt.setInt(2, bike.getId());
            prSt.setInt(3, user.getId());
            prSt.setString(4, bike.getAdditionalInfo());

            System.out.println(prSt.toString());

            result = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        result.next();
        return result;
    }

    public boolean createRentBike(Bicycle bike, ShortUser user) throws SQLException, ClassNotFoundException {
        try {
            String getIdShopsIntoBike = "SELECT " + BIKE_IDSHOS + " FROM "
                     + BIKE_TABLE + " WHERE " + BIKE_ID + " = ?;";

            String createRent = "INSERT INTO " + RESERVATION_TABLE + "("
                    + RESERVATION_IDBIKES + ", " + RESERVATION_IDUSER+ ", "
                    +RESERVATION_IDSHOPS + ", " + RESERVATION_DATERECEIPT + ") VALUES (?, ?, ?, ?);";

            String changeStatusBike = "UPDATE " + BIKE_TABLE + " SET "
                    + BIKE_STATUS + " = ? WHERE " + BIKE_ID + " = ?;";


            PreparedStatement prSt = getDbConnection().prepareStatement(getIdShopsIntoBike);
            prSt.setInt(1, bike.getId());

            ResultSet idShopResultSet = prSt.executeQuery();
            idShopResultSet.next();


            prSt = getDbConnection().prepareStatement(createRent);
            prSt.setInt(1, bike.getId());
            prSt.setInt(2, user.getId());
            prSt.setInt(3, idShopResultSet.getInt(BIKE_IDSHOS));
            prSt.setDate(4, Date.valueOf(LocalDate.now()));

            prSt.executeUpdate();


            prSt = getDbConnection().prepareStatement(changeStatusBike);
            prSt.setString(1, "busy");
            prSt.setInt(2, bike.getId());

            prSt.executeUpdate();

        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            errorAlert();
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public void errorAlert() {
        Alert error = new Alert(Alert.AlertType.ERROR, "Ошибка при работе с базой данных", ButtonType.OK);
        error.showAndWait();
    }

    public ResultSet getShopsInfo() throws SQLException, ClassNotFoundException {
        ResultSet result = null;
        try {
            String getShopsInfo = "SELECT * FROM " + SHOPS_TABLE;

            PreparedStatement prSt = getDbConnection().prepareStatement(getShopsInfo);
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

    public ResultSet getBikeRentedByUserCloseRentBike(ShortUser user) {
        ResultSet result = null;
        try{
            String intermediateTableSubquery = "bikeAndReserv";
            String getRentBike = "SELECT "
                    + intermediateTableSubquery + "." + BIKE_ID + ", "
                    + intermediateTableSubquery + "." + BIKE_NAME + ", "
                    + intermediateTableSubquery + "." + BIKE_MODEL + ", "
                    + intermediateTableSubquery + "." + BIKE_TYPE + ", "
                    + intermediateTableSubquery + "." + BIKE_NUMBERGEAR + ", "
                    + intermediateTableSubquery + "." + BIKE_STATUS + ", "
                    + intermediateTableSubquery + "." + BIKE_PRICEHOUR + ", "
                    + intermediateTableSubquery + "." + RESERVATION_DATERECEIPT
                    + " FROM " + USER_TABLE + " JOIN (SELECT "
                    + BIKE_TABLE + "." + BIKE_ID + ", "
                    + BIKE_TABLE + "." + BIKE_NAME + ", "
                    + BIKE_TABLE + "." + BIKE_MODEL + ", "
                    + BIKE_TABLE + "." + BIKE_TYPE + ", "
                    + BIKE_TABLE + "." + BIKE_NUMBERGEAR + ", "
                    + BIKE_TABLE + "." + BIKE_STATUS + ", "
                    + BIKE_TABLE + "." + BIKE_PRICEHOUR + ", "
                    + RESERVATION_TABLE + "." + RESERVATION_DATERECEIPT + ", "
                    + RESERVATION_TABLE + "." + RESERVATION_IDUSER
                    + " FROM " + BIKE_TABLE + " JOIN " + RESERVATION_TABLE + " ON "
                    + BIKE_TABLE + "." + BIKE_STATUS + " = ? AND "
                    + BIKE_TABLE + "." + BIKE_ID + " = "
                    + RESERVATION_TABLE + "." + RESERVATION_IDBIKES + ") AS " + intermediateTableSubquery
                    + " ON " + intermediateTableSubquery + "." + RESERVATION_IDUSER + " = "
                    + USER_TABLE + "." + USER_ID + " WHERE "
                    + USER_TABLE + "." + USER_ID + " = ?;";
            System.out.println(getRentBike);
            PreparedStatement prSt = getDbConnection().prepareStatement(getRentBike);
            prSt.setString(1, "busy");
            prSt.setInt(2,user.getId());

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
