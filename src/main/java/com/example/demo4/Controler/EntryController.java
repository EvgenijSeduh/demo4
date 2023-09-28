package com.example.demo4.Controler;


import com.example.demo4.Model.ModelAutorizateUser;
import com.example.demo4.Model.ModelEmployeePanel;
import com.example.demo4.Recource.Const.ConstAllTable;
import com.example.demo4.Recource.InfoUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
            InfoUser infoUser = new InfoUser();
            infoUser.setLogin(stringLogin.getText().trim());
            try {
                infoUser.setPassword(stringPassword.getText().trim());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            if(infoUser.isFillAutorization()){
                try {
                    if(checkUser(infoUser)){
                        buttonRegistration.getScene().getWindow().hide();

                        FXMLLoader loader = new FXMLLoader();
                        try {
                            String mandat = checkMandat(infoUser);
                            ModelEmployeePanel.setMandat(mandat);
                            if (mandat.equals("client")) {
                                loader.setLocation(getClass().getResource("/com/example/demo4/Viev/ClientPanel.fxml"));
                                loader.load();
                                Parent root = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setTitle("Панель клиента");
                                stage.setResizable(false);
                                stage.setScene(new Scene(root));
                                stage.show();
                            } else if (mandat.equals("employee")) {
                                loader.setLocation(getClass().getResource("/com/example/demo4/Viev/EmployeePanel.fxml"));
                                loader.load();
                                Parent root = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setTitle("Панель сотрудника");
                                stage.setResizable(false);
                                stage.setScene(new Scene(root));
                                stage.show();
                            } else if (mandat.equals("super_user")) {
                                loader.setLocation(getClass().getResource("/com/example/demo4/Viev/AdminPanel.fxml"));
                                loader.load();
                                Parent root = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setTitle("Панель администратора");
                                stage.setResizable(false);
                                stage.setScene(new Scene(root));
                                stage.show();
                            }
                            else{
                                Alert unknowMandat = new Alert(Alert.AlertType.ERROR, "Не удалось узнать мандат", ButtonType.OK);
                                unknowMandat.showAndWait();
                            }

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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

    private void fillInfoAboutUser(InfoUser infoUser)  throws ClassNotFoundException,SQLException{
        ModelAutorizateUser modelAutorizateUser = new ModelAutorizateUser();
        ResultSet userInfo = modelAutorizateUser.getDBInfoUser();
        userInfo.next();
    }

    private boolean checkUser(InfoUser infoUser) throws SQLException, ClassNotFoundException {
        ModelAutorizateUser modelAutorizateUser = new ModelAutorizateUser();
        return modelAutorizateUser.checkUser(infoUser);
    }

    public String checkMandat(InfoUser infoUser) throws SQLException, ClassNotFoundException {
        ModelAutorizateUser modelAutorizateUser = new ModelAutorizateUser();
        return modelAutorizateUser.checkMandat(infoUser);
    }

}
