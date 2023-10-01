package com.example.demo4.Model;


import com.example.demo4.Recource.Const.ConstAllTable;
import com.example.demo4.Recource.DataBaseHandler;
import com.example.demo4.Recource.InfoUser;

import java.sql.*;

import static java.lang.Integer.parseInt;

public class ModelRegistrationUser extends ConstAllTable {
    DataBaseHandler dataBaseHandler = DataBaseHandler.getInstance();

    public ModelRegistrationUser() throws SQLException, ClassNotFoundException {
    }

    public boolean signUpUser(InfoUser infoUser){


        try {
            String setPassportsDate = "INSERT INTO "
                    + PASSPORT_TABLE + " ("
                    + PASSPORT_SERIES + ", "
                    + PASSPORT_NUMBER
                    + ") VALUES (?, ?);";

            String getIdPassport = "SELECT MAX(" + PASSPORT_ID + ") AS " + PASSPORT_ID + " FROM "
                    + PASSPORT_TABLE
                    + " WHERE " + PASSPORT_SERIES + "=? AND "
                    + PASSPORT_NUMBER + "=?;";

            String setUserDate = "INSERT INTO "
                    + USER_TABLE + " ("
                    + USER_IDPASSPORT + ", "
                    + USER_NAME + ", "
                    + USER_COUNTRY + ", "
                    + USER_CITY + ", "
                    + USER_STREET + ", "
                    + USER_HOME + ", "
                    + USER_MANDAT
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?);";

            String getIdUser = "SELECT MAX(" +  USER_ID+ ")  AS " + PASSPORT_ID + " FROM " + USER_TABLE
                    + " WHERE " + USER_IDPASSPORT + " = ? AND "
                    + USER_NAME + " = ? AND "
                    + USER_COUNTRY + " = ? AND "
                    + USER_CITY + " = ? AND "
                    + USER_STREET + " = ? AND "
                    + USER_HOME + " = ?;";

            String setAutorizationsDate = "INSERT INTO "
                    + AUTORIZATIONS_TABLE + " ("
                    + AUTORIZATIONS_IDUSER + ", "
                    + AUTORIZATIONS_LOGIN + ", "
                    + AUTORIZATIONS_PASSWORD
                    + ") VALUES (?, ?, ?);";

            Statement statement = dataBaseHandler.getConnection().createStatement();

//-----------------------------------------------------------------------
            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(setPassportsDate);
            prSt.setString(1, infoUser.getSeriesPass());
            prSt.setString(2, infoUser.getNuberPass());

            prSt.executeUpdate();
//-----------------------------------------------------------------------
            prSt = dataBaseHandler.getConnection().prepareStatement(getIdPassport);
            prSt.setString(1, infoUser.getSeriesPass());
            prSt.setString(2, infoUser.getNuberPass());

//            System.out.println(parseInt(infoUser.getSeriesPass()) + "|" +parseInt(infoUser.getNuberPass()));
//            System.out.println(prSt.getWarnings());
//            System.out.println(getIdPassport);

            ResultSet idPassport = prSt.executeQuery();
            idPassport.next();
            System.out.println();
            System.out.println(idPassport.getString(1));
            System.out.println(idPassport.getMetaData().getColumnName(1));
            System.out.println(idPassport.getInt(PASSPORT_ID));
//----------------------------------------------------------------------
            prSt = dataBaseHandler.getConnection().prepareStatement(setUserDate);
            prSt.setInt(1, idPassport.getInt(PASSPORT_ID));
            prSt.setString(2, infoUser.getName());
            prSt.setString(3, infoUser.getCountry());
            prSt.setString(4, infoUser.getCity());
            prSt.setString(5, infoUser.getStreet());
            prSt.setString(6, infoUser.getHome());
            prSt.setString(7, infoUser.getMandat());

            prSt.executeUpdate();
//----------------------------------------------------------------------
            prSt = dataBaseHandler.getConnection().prepareStatement(getIdUser);
            prSt.setInt(1, idPassport.getInt(PASSPORT_ID));
            prSt.setString(2, infoUser.getName());
            prSt.setString(3, infoUser.getCountry());
            prSt.setString(4, infoUser.getCity());
            prSt.setString(5, infoUser.getStreet());
            prSt.setString(6, infoUser.getHome());

            ResultSet idUser = prSt.executeQuery();
            idUser.next();
// ---------------------------------------------------------------------
            prSt = dataBaseHandler.getConnection().prepareStatement(setAutorizationsDate);
            prSt.setInt(1, parseInt(idUser.getString(USER_ID)));
            prSt.setString(2, infoUser.getLogin());
            prSt.setBytes(3, infoUser.getPassword());

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

    }


}
