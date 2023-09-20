package com.example.demo4.Model;

import com.example.demo4.User;

import java.sql.*;

import static java.lang.Integer.parseInt;

public class ModelRegistrationUser extends ConstARClient {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException,SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;


        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);

        return  dbConnection;
    }

    public boolean signUpUser(User user){


        try {
            String setPassportsDate = "INSERT INTO "
                    + ConstARClient.PASSPORT_TABLE + " ("
                    + ConstARClient.PASSPORT_SERIES + ", "
                    + ConstARClient.PASSPORT_NUMBER
                    + ") VALUES (?, ?);";

            String getIdPassport = "SELECT MAX(" + PASSPORT_ID + ") AS " + PASSPORT_ID + " FROM "
                    + ConstARClient.PASSPORT_TABLE
                    + " WHERE " + ConstARClient.PASSPORT_SERIES + "=? AND "
                    + ConstARClient.PASSPORT_NUMBER + "=?;";

            String setUserDate = "INSERT INTO "
                    + ConstARClient.USER_TABLE + " ("
                    + ConstARClient.USER_IDPASSPORT + ", "
                    + ConstARClient.USER_NAME + ", "
                    + ConstARClient.USER_COUNTRY + ", "
                    + ConstARClient.USER_CITY + ", "
                    + ConstARClient.USER_STREET + ", "
                    + ConstARClient.USER_HOME
                    + ") VALUES (?, ?, ?, ?, ?, ?);";

            String getIdUser = "SELECT MAX(" +  USER_ID+ ")  AS " + PASSPORT_ID + " FROM " + ConstARClient.USER_TABLE
                    + " WHERE " + ConstARClient.USER_IDPASSPORT + " = ? AND "
                    + ConstARClient.USER_NAME + " = ? AND "
                    + ConstARClient.USER_COUNTRY + " = ? AND "
                    + ConstARClient.USER_CITY + " = ? AND "
                    + ConstARClient.USER_STREET + " = ? AND "
                    + ConstARClient.USER_HOME + " = ?;";

            String setAutorizationsDate = "INSERT INTO "
                    + ConstARClient.AUTORIZATIONS_TABLE + " ("
                    + ConstARClient.AUTORIZATIONS_IDUSER + ", "
                    + ConstARClient.AUTORIZATIONS_LOGIN + ", "
                    + ConstARClient.AUTORIZATIONS_PASSWORD
                    + ") VALUES (?, ?, ?);";

            Statement statement = getDbConnection().createStatement();

//-----------------------------------------------------------------------
            PreparedStatement prSt = getDbConnection().prepareStatement(setPassportsDate);
            prSt.setString(1, user.getSeriesPass());
            prSt.setString(2, user.getNuberPass());

            prSt.executeUpdate();
//-----------------------------------------------------------------------
            prSt = getDbConnection().prepareStatement(getIdPassport);
            prSt.setString(1, user.getSeriesPass());
            prSt.setString(2, user.getNuberPass());

//            System.out.println(parseInt(user.getSeriesPass()) + "|" +parseInt(user.getNuberPass()));
//            System.out.println(prSt.getWarnings());
//            System.out.println(getIdPassport);

            ResultSet idPassport = prSt.executeQuery();
            idPassport.next();
            System.out.println();
            System.out.println(idPassport.getString(1));
            System.out.println(idPassport.getMetaData().getColumnName(1));
            System.out.println(idPassport.getInt(ConstARClient.PASSPORT_ID));
//----------------------------------------------------------------------
            prSt = getDbConnection().prepareStatement(setUserDate);
            prSt.setInt(1, idPassport.getInt(ConstARClient.PASSPORT_ID));
            prSt.setString(2, user.getName());
            prSt.setString(3, user.getCountry());
            prSt.setString(4, user.getCity());
            prSt.setString(5, user.getStreet());
            prSt.setString(6, user.getHome());

            prSt.executeUpdate();
//----------------------------------------------------------------------
            prSt = getDbConnection().prepareStatement(getIdUser);
            prSt.setInt(1, idPassport.getInt(ConstARClient.PASSPORT_ID));
            prSt.setString(2, user.getName());
            prSt.setString(3, user.getCountry());
            prSt.setString(4, user.getCity());
            prSt.setString(5, user.getStreet());
            prSt.setString(6, user.getHome());

            ResultSet idUser = prSt.executeQuery();
            idUser.next();
// ---------------------------------------------------------------------
            prSt = getDbConnection().prepareStatement(setAutorizationsDate);
            prSt.setInt(1, parseInt(idUser.getString(ConstARClient.USER_ID)));
            prSt.setString(2, user.getLogin());
            prSt.setString(3, user.getPassword());

            prSt.executeUpdate();
//----------------------------------------------------------------------

            statement.close();
            prSt.close();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }
}
