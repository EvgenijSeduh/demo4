package com.example.demo4.Controler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfilePanelController {

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonRedactLogin;

    @FXML
    private Button buttonRedactName;

    @FXML
    private Button buttonRedactPassword;

    @FXML
    private TextField login;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    void initialize(){
        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();

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
        });
    }
}