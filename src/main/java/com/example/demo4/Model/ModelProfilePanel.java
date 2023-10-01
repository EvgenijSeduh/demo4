package com.example.demo4.Model;

import com.example.demo4.Recource.Const.ConstAllTable;
import com.example.demo4.Recource.DataBaseHandler;
import com.example.demo4.Recource.InfoUser;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModelProfilePanel extends ConstAllTable {

    DataBaseHandler dataBaseHandler = DataBaseHandler.getInstance();

    public ModelProfilePanel() throws SQLException, ClassNotFoundException {

    }

    public boolean changeLogin(String loginText) {
        try {
            String changeLoginSQL = "UPDATE " + AUTORIZATIONS_TABLE + " SET "
                    + AUTORIZATIONS_LOGIN + " = ? WHERE " + AUTORIZATIONS_ID + " = ?";
            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(changeLoginSQL);
            prSt.setString(1,loginText);
            prSt.setInt(2,ModelAutorizateUser.getIdAutorizate());

            prSt.executeUpdate();

        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean changePassword(String passwordText) {
        try {
            InfoUser infoUser = new InfoUser();
            String changeLoginSQL = "UPDATE " + AUTORIZATIONS_TABLE + " SET "
                    + AUTORIZATIONS_PASSWORD+ " = ? WHERE " + AUTORIZATIONS_ID + " = ?";
            PreparedStatement prSt = dataBaseHandler.getConnection().prepareStatement(changeLoginSQL);
            prSt.setBytes(1,infoUser.hash(passwordText));
            prSt.setInt(2,ModelAutorizateUser.getIdAutorizate());

            prSt.executeUpdate();

        } catch (SQLException e) {
            errorAlert();
            e.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            errorAlert();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void errorAlert() {
        Alert error = new Alert(Alert.AlertType.ERROR, "Ошибка при работе с базой данных", ButtonType.OK);
        error.showAndWait();
    }
}
