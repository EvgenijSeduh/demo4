package com.example.demo4.Model;

import com.example.demo4.Recource.Const.ConstAllTable;


import java.sql.*;

public class ModelClientPanel extends ConstAllTable {
    Connection dbConnection;


    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;


        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
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

}
