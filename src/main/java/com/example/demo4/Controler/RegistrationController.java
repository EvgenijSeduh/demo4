package com.example.demo4.Controler;

import com.example.demo4.Model.ModelRegistrationUser;
import com.example.demo4.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
            user.setLogin(stringLogin.getText().trim());
            user.setPassword(stringPassword.getText().trim());
            user.setName(stringName.getText().trim());
            user.setCountry(stringCountry.getText().trim());
            user.setCity(stringCity.getText().trim());
            user.setStreet(stringStreet.getText().trim());
            user.setHome(stringHome.getText().trim());
            user.setSeriesPass(stringSeriesPassport.getText().trim());
            user.setNumberPass(stringNumberPassport.getText().trim());

            if(user.isFill())
                if(singUpUser(user))
                    System.out.println("Вы зарегестрированны");
                else
                    System.out.println("Вы не зарегестрированны");
            else
                System.out.println("There are empty fields");
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

