package com.example.demo4.Controler;

import com.example.demo4.Model.ModelEmployeePanel;
import com.example.demo4.Model.ModelProfilePanel;
import com.example.demo4.Recource.Requirements;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

//package com.example.demo4.Controler;


public class ProfilePanelController extends Requirements {

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonRedactLogin;

    @FXML
    private Button buttonRedactPassword;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    void initialize(){

        buttonExit.setOnAction(event -> {
            String mandat = ModelEmployeePanel.getMandat();
            String title = "";
            buttonExit.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            if(mandat.equals("super_user")) {
                loader.setLocation(getClass().getResource("/com/example/demo4/Viev/AdminPanel.fxml"));
                title = "Панель админа";
            }
            else if(mandat.equals("employee")) {
                loader.setLocation(getClass().getResource("/com/example/demo4/Viev/EmployeePanel.fxml"));
                title = "Панель сотрудника";
            }else if(mandat.equals("client")){
                loader.setLocation(getClass().getResource("/com/example/demo4/Viev/ClientPanel.fxml"));
                title = "Панель клиента";
            }
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        });

        buttonRedactLogin.setOnAction(event -> {
            String loginText = login.getText().trim();
            if(loginText != null){
                if(loginText.matches(loginRegular)){
                    ModelProfilePanel modelProfilePanel = null;
                    try {
                        modelProfilePanel = new ModelProfilePanel();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    if(modelProfilePanel.changeLogin(loginText)){
                        Alert doneChangeLogin = new Alert(Alert.AlertType.INFORMATION,"Логин упешно изменен", ButtonType.OK);
                        doneChangeLogin.showAndWait();
                    }
                    else{
                        Alert errorChangeLogin = new Alert(Alert.AlertType.ERROR,"Не удалось изменить логин", ButtonType.OK);
                        errorChangeLogin.showAndWait();
                    }
                }
            }

        });

        buttonRedactPassword.setOnAction(event -> {
            String passwordText = password.getText().trim();
            if(passwordText != null){
                if(passwordText.matches(passwordRegular)){
                    ModelProfilePanel modelProfilePanel = null;
                    try {
                        modelProfilePanel = new ModelProfilePanel();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    if(modelProfilePanel.changePassword(passwordText)){
                        Alert doneChangeLogin = new Alert(Alert.AlertType.INFORMATION,"Пароль упешно изменен", ButtonType.OK);
                        doneChangeLogin.showAndWait();
                    }
                    else{
                        Alert errorChangeLogin = new Alert(Alert.AlertType.ERROR,"Не удалось изменить пароль", ButtonType.OK);
                        errorChangeLogin.showAndWait();
                    }
                }
            }
        });
    }
}