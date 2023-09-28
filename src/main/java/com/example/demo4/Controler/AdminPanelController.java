package com.example.demo4.Controler;

import com.example.demo4.Model.AdminPanelModel;
import com.example.demo4.Recource.Bicycle;
import com.example.demo4.Recource.Const.ConstAllTable;
import com.example.demo4.Recource.Shop;
import com.example.demo4.Recource.User;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPanelController extends ConstAllTable {

    @FXML
    private TableColumn<Bicycle, String> additionalInfoBikeTable;

    @FXML
    private TableColumn<User, String> addressUser;

    @FXML
    private Button buttonAddBike;

    @FXML
    private Button buttonDeleteBikeTable;

    @FXML
    private Button buttonDeleteUser;

    @FXML
    private Button buttonEmployeePanel;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonProfile;

    @FXML
    private Button buttonRaiseStatusUser;

    @FXML
    private Button buttonUpdateBikeTable;

    @FXML
    private Button buttonUpdateUser;

    @FXML
    private Button buttonUpdateBikeAddBike;

    @FXML
    private Button buttonUpdateUserTable2;

    @FXML
    private TableColumn<?, ?> changeShopRent;

    @FXML
    private TableColumn<Bicycle, String> configBikeTable;

    @FXML
    private TableColumn<?, ?> idBikeRent;

    @FXML
    private TableColumn<Bicycle, Integer> idBikeTable;

    @FXML
    private TableColumn<User, Integer> idUser;

    @FXML
    private TableView<Bicycle> mainTableBike;

    @FXML
    private TableView<?> mainTableRent;

    @FXML
    private TableView<User> mainTableUser;

    @FXML
    private TextField modelFromAdd;

    @FXML
    private TableColumn<?, ?> nameBikeRent;

    @FXML
    private TableColumn<Bicycle, String> nameBikeTable;

    @FXML
    private TextField nameFromAdd;

    @FXML
    private TableColumn<User, String> nameUser;

    @FXML
    private TextField numbergearForAdd;

    @FXML
    private TableColumn<User, String> passportUser;

    @FXML
    private TableColumn<Bicycle, Integer> priceBikeTable;

    @FXML
    private TextField priceForAdd;

    @FXML
    private TableColumn<?, ?> sourceShopRent;

    @FXML
    private TableColumn<Bicycle, String> statusBikeTable;

    @FXML
    private TableColumn<User, String> statusUser;

    @FXML
    private TableColumn<?, ?> termRent;

    @FXML
    private TextField typeFromAdd;

    @FXML
    private TableColumn<?, ?> userRent;

    @FXML
    private ComboBox<Shop> shopListFromAdd;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<Bicycle> bikeList = FXCollections.observableArrayList();
        ObservableList<User> userTableListUserTable = FXCollections.observableArrayList();

        idUser.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nameUser.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        passportUser.setCellValueFactory(new PropertyValueFactory<User, String>("passport"));
        addressUser.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        statusUser.setCellValueFactory(new PropertyValueFactory<User, String>("status"));

        mainTableUser.setItems(userTableListUserTable);//просмотр всех пользователей

        idBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle,Integer>("id"));
        nameBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle,String>("name"));
        configBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle,String>("config"));
        statusBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle,String>("status"));
        priceBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle,Integer>("price"));
        additionalInfoBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle,String>("additionalInfo"));
//
        mainTableBike.setItems(bikeList);


        ObservableList<Shop> allShopsList = FXCollections.observableArrayList();
        getAllShops(allShopsList);
        shopListFromAdd.itemsProperty().bindBidirectional(new SimpleListProperty<Shop>(allShopsList));
        shopListFromAdd.setConverter(new StringConverter<Shop>() {
            @Override
            public String toString(Shop shop) {
                return shop != null ? shop.getName() + "\n" + shop.getAddress() : "";
            }

            @Override
            public Shop fromString(String string) {
                return null;
            }
        });

        updateUserTable(userTableListUserTable);
        updateBikeTable(bikeList);

        buttonAddBike.setOnAction(event -> {
            if (nameFromAdd.getText().trim() != null && typeFromAdd.getText().trim() != null &&
                    modelFromAdd.getText().trim() != null && numbergearForAdd.getText().trim() != null &&
                    priceForAdd.getText().trim() != null && shopListFromAdd.getValue() != null) {

                int selectedShopId = shopListFromAdd.getValue().getId();

                if (addBike(nameFromAdd.getText().trim(), typeFromAdd.getText().trim(),
                        modelFromAdd.getText().trim(), numbergearForAdd.getText().trim(),
                        priceForAdd.getText().trim(), selectedShopId)) {


                    Alert doneAdd = new Alert(Alert.AlertType.INFORMATION, "Запись добавлена", ButtonType.OK);
                    updateBikeTable(bikeList);
                    doneAdd.showAndWait();
                } else {
                    Alert errorAdd = new Alert(Alert.AlertType.ERROR, "Запись не была добавлена", ButtonType.OK);
                    errorAdd.showAndWait();
                }
            }
            else{
                Alert emptyField = new Alert(Alert.AlertType.ERROR,"Заполните все поля", ButtonType.OK);
                emptyField.showAndWait();
            }
        });

        buttonUpdateBikeAddBike.setOnAction(event -> {
            updateBikeTable(bikeList);
        });

        buttonUpdateUser.setOnAction(event -> {
            updateUserTable(userTableListUserTable);
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

        buttonEmployeePanel.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo4/Viev/EmployeePanel.fxml"));
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
    }

    private boolean addBike(String name, String type, String model, String numbergear, String price, int idshops) {
        AdminPanelModel adminPanelController = new AdminPanelModel();
        return adminPanelController.addBike(name,type,model,numbergear,price,idshops);
    }

    private void getAllShops(ObservableList<Shop> allShopsList) throws SQLException {
        AdminPanelModel adminPanelController = new AdminPanelModel();
        ResultSet allShopRes = adminPanelController.getAllShops();

        while(allShopRes.next()){
            allShopsList.add(new Shop(
                    allShopRes.getInt(SHOPS_ID),
                    allShopRes.getString(SHOPS_NAME),
                    allShopRes.getString(SHOPS_ADDRESS)
            ));
        }
    }

    public void updateBikeTable(ObservableList list){
        try {
            list.clear();
            AdminPanelModel adminPanelModel = new AdminPanelModel();
            ResultSet bikeInfo = adminPanelModel.getBusyBikeInfo();
            while(bikeInfo.next()){
                list.add(new Bicycle(bikeInfo.getInt(BIKE_ID),
                        bikeInfo.getString(BIKE_NAME),
                        "Модель: " + bikeInfo.getString(BIKE_MODEL) + "\nТип: " + bikeInfo.getString(BIKE_TYPE) + "\nКол-во передач: " + bikeInfo.getInt(BIKE_NUMBERGEAR),
                        bikeInfo.getString(BIKE_STATUS),
                        bikeInfo.getInt(BIKE_PRICEHOUR),
                        bikeInfo.getString("MAX(" + RESERVATION_TABLE + "." + RESERVATION_DATERECEIPT + ")")));
                System.out.println("Модель: " + bikeInfo.getString(BIKE_MODEL) + "\nТип: " + bikeInfo.getString(BIKE_TYPE) + "\nКол-во передач: " + bikeInfo.getInt(BIKE_NUMBERGEAR));
            }

            bikeInfo = adminPanelModel.getFreeBikeInfo();
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

    public void updateUserTable(ObservableList<User> userTableList) {
        try {
            userTableList.clear();
            AdminPanelModel adminPanelModel = new AdminPanelModel();
            ResultSet userInfo = adminPanelModel.getUserInfo();
            while (userInfo.next()) {
                userTableList.add(new User(
                        userInfo.getInt(USER_ID),
                        userInfo.getString(USER_NAME),
                        userInfo.getString(PASSPORT_SERIES) + " " + userInfo.getString(PASSPORT_NUMBER),
                        userInfo.getString(USER_COUNTRY) + " г. " + userInfo.getString(USER_CITY) + " ул. " + userInfo.getString(USER_STREET) + " д. " + userInfo.getString(USER_HOME),
                        userInfo.getString(USER_MANDAT)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
