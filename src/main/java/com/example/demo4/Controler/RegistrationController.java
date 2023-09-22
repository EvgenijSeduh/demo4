package com.example.demo4.Controler;

import com.example.demo4.Model.ModelRegistrationUser;
import com.example.demo4.Recource.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class RegistrationController {

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonRegistration;

    @FXML
    private TextField stringCity;

    @FXML
    private TextField stringCountry;

    @FXML
    private TextField stringHome;

    @FXML
    private TextField stringLogin;

    @FXML
    private TextField stringName;

    @FXML
    private TextField stringNumberPassport;

    @FXML
    private TextField stringPassword;

    @FXML
    private TextField stringSeriesPassport;

    @FXML
    private TextField stringStreet;
    @FXML
    void initialize(){
        buttonRegistration.setOnAction(event ->{
            User user = new User();

            System.out.println(user.setName(stringName.getText().trim())?"Имя: \tV":"Имя: \tХ");
            System.out.println(user.setCountry(stringCountry.getText().trim())?"Страна: \tV":"Страна: \tХ");
            System.out.println(user.setCity(stringCity.getText().trim())?"Город: \tV":"Город: \tХ");
            System.out.println(user.setStreet(stringStreet.getText().trim())?"Улица: \tV":"Улица: \tХ");
            System.out.println(user.setHome(stringHome.getText().trim())?"Дом: \tV":"Дом: \tХ");
            System.out.println(user.setSeriesPass(stringSeriesPassport.getText().trim())?"Номер паспорта: \tV":"Номер паспорта: \tХ");
            System.out.println(user.setNumberPass(stringNumberPassport.getText().trim())?"Серия паспорта: \tV":"Серия паспорта: \tХ");
            System.out.println(user.setLogin(stringLogin.getText().trim())?"Логин: \tV":"Логин: \tХ");
            try {
                System.out.println(user.setPassword(stringPassword.getText().trim())?"Пароль: \tV":"Пароль: \tХ");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            if(user.isFill())
                if(singUpUser(user))
                    System.out.println("Вы зарегистрированы");
                else
                    System.out.println("Вы не зарегистрированы");
            else
                System.out.println("Заполнены не все поля");
        });

        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo4/Viev/EntryWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Авторизация");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    private boolean singUpUser(User user) {
        ModelRegistrationUser modelRegistrationUser = new ModelRegistrationUser();
        return modelRegistrationUser.signUpUser(user);
    }


}

