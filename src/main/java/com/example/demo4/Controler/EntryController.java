package com.example.demo4.Controler;


import com.example.demo4.Model.ModelAutorizateUser;
import com.example.demo4.Recource.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntryController {

    @FXML
    private Button buttonEntry;

    @FXML
    private Button buttonRegistration;

    @FXML
    private TextField stringLogin;

    @FXML
    private PasswordField stringPassword;

    @FXML
    void initialize(){
        buttonEntry.setOnAction(event -> {
            User user = new User();
            user.setLogin(stringLogin.getText().trim());
            try {
                user.setPassword(stringPassword.getText().trim());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            if(user.isFillAutorization()){
                try {
                    if(checkUser(user)){
                        //fillInUser(user);

                        buttonRegistration.getScene().getWindow().hide();

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/demo4/Viev/ClientPanel.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setTitle("Панель клиента");
                        stage.setResizable(false);
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                    else
                        System.out.println("Логин и/или пароль неверный");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            else
                System.out.println("Login or password is entry");
        });

        buttonRegistration.setOnAction(event -> {
            buttonRegistration.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo4/Viev/RegistrationWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Регистрация");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        });

    }

    private void fillInfoAboutUser(User user)  throws ClassNotFoundException,SQLException{
        ModelAutorizateUser modelAutorizateUser = new ModelAutorizateUser();
        ResultSet userInfo = modelAutorizateUser.getDBInfoUser();
        userInfo.next();
    }

    private boolean checkUser(User user) throws SQLException, ClassNotFoundException {
        ModelAutorizateUser modelAutorizateUser = new ModelAutorizateUser();
        return modelAutorizateUser.checkUser(user);
    }

}
