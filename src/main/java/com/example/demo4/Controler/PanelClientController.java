package com.example.demo4.Controler;

import com.example.demo4.Model.PanelClientModel;
import com.example.demo4.Recource.Bicycle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class PanelClientController {

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonProfile;

    @FXML
    private Button buttonUpdate;
    @FXML
    private TableView<Bicycle> tableBicycle;
    @FXML
    private TableColumn<Bicycle, Integer> tableId;

    @FXML
    private TableColumn<Bicycle, String> tableEquipment;

    @FXML
    private TableColumn<Bicycle, String> tableInfo;

    @FXML
    private TableColumn<Bicycle, String> tableName;

    @FXML
    private TableColumn<Bicycle, String> tableStatus;

    @FXML
    private TableColumn<Bicycle, Integer> tablePrice;

    @FXML
    void initialize() {
        Bicycle bike = new Bicycle();
        ObservableList<Bicycle> list = FXCollections.observableArrayList();

        tableBicycle.setItems(list);

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

        buttonProfile.setOnAction(event -> {
            buttonProfile.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo4/Viev/ProfilePanel.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Профиль");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        });

        buttonUpdate.setOnAction(event -> {
            PanelClientModel panelClientModel = new PanelClientModel();
            panelClientModel.getBikeInfo();
        });
    }

}
