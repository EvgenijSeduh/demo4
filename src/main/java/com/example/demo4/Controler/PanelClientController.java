package com.example.demo4.Controler;

import com.example.demo4.Model.ModelClientPanel;
import com.example.demo4.Recource.Bicycle;
import com.example.demo4.Recource.Const.ConstAllTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PanelClientController extends ConstAllTable {

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
    private TableColumn<Bicycle, String> tableName;
    @FXML
    private TableColumn<Bicycle, String> tableConfig;

    @FXML
    private TableColumn<Bicycle, String> tableStatus;
    @FXML
    private TableColumn<Bicycle, Integer> tablePrice;
    @FXML
    private TableColumn<Bicycle, String> tableAdditionalInfo;

    @FXML
    void initialize() {
        ObservableList<Bicycle> list = FXCollections.observableArrayList();

        tableId.setCellValueFactory(new PropertyValueFactory<Bicycle,Integer>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<Bicycle,String>("name"));
        tableConfig.setCellValueFactory(new PropertyValueFactory<Bicycle,String>("config"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<Bicycle,String>("status"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<Bicycle,Integer>("price"));
        tableAdditionalInfo.setCellValueFactory(new PropertyValueFactory<Bicycle,String>("additionalInfo"));
//
        tableBicycle.setItems(list);

        updateBikeTable(list);

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
            updateBikeTable(list);
        });
    }

    public void updateBikeTable(ObservableList list){
        try {
            list.clear();
            ModelClientPanel modelClientPanel = new ModelClientPanel();
            ResultSet bikeInfo = modelClientPanel.getBusyBikeInfo();
            while(bikeInfo.next()){
                list.add(new Bicycle(bikeInfo.getInt(BIKE_ID),
                        bikeInfo.getString(BIKE_NAME),
                        "Модель: " + bikeInfo.getString(BIKE_MODEL) + "\nТип: " + bikeInfo.getString(BIKE_TYPE) + "\nКол-во передач: " + bikeInfo.getInt(BIKE_NUMBERGEAR),
                        bikeInfo.getString(BIKE_STATUS),
                        bikeInfo.getInt(BIKE_PRICEHOUR),
                        bikeInfo.getString("MAX(" + RESERVATION_TABLE + "." + RESERVATION_DATERECEIPT + ")")));
                System.out.println("Модель: " + bikeInfo.getString(BIKE_MODEL) + "\nТип: " + bikeInfo.getString(BIKE_TYPE) + "\nКол-во передач: " + bikeInfo.getInt(BIKE_NUMBERGEAR));
            }

            bikeInfo = modelClientPanel.getFreeBikeInfo();
            while(bikeInfo.next()){
                list.add(new Bicycle(bikeInfo.getInt(BIKE_ID),
                        bikeInfo.getString(BIKE_NAME),
                        ("Модель: " + bikeInfo.getString(BIKE_MODEL) + "\nТип: " + bikeInfo.getString(BIKE_TYPE) + "\nКол-во передач: " + bikeInfo.getInt(BIKE_NUMBERGEAR)),
                        bikeInfo.getString(BIKE_STATUS),
                        bikeInfo.getInt(BIKE_PRICEHOUR),
                        bikeInfo.getString(SHOPS_ADDRESS)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
