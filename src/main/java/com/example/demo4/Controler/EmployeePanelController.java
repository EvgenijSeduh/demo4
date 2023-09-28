package com.example.demo4.Controler;

import com.example.demo4.Model.ModelEmployeePanel;
import com.example.demo4.Recource.Bicycle;

import com.example.demo4.Recource.Const.ConstAllTable;
import com.example.demo4.Recource.Shop;
import com.example.demo4.Recource.ShortUser;
import com.example.demo4.Recource.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class EmployeePanelController extends ConstAllTable {
    @FXML
    private TableView<Bicycle> mainTableBike;

    @FXML
    private TableView<Bicycle> mainTableBikeCloseRentBike;

    @FXML
    private TableView<Bicycle> mainTableBikeRentBike;

    @FXML
    private TableView<Shop> mainTableShopCloseRentBike;

    @FXML
    private TableView<User> mainTableUser;

    @FXML
    private TableView<ShortUser> mainTableUserCloseRentBike;

    @FXML
    private TableView<ShortUser> mainTableUserRentBike;

    @FXML
    private Button buttonCloseRentCloseRentBike;

    @FXML
    private TableColumn<Bicycle, Integer> idBikeCloseRentBike;

    @FXML
    private TableColumn<Shop, Integer> idShopCloseRentBike;

    @FXML
    private TableColumn<ShortUser, Integer> idUserCloseRentBike;

    @FXML
    private TableColumn<Bicycle, String> additionalInfoBikeTable;

    @FXML
    private TableColumn<Shop, String> addressShopCloseRentBike;

    @FXML
    private TableColumn<User, String> addressUserTable;

    @FXML
    private Button buttonAddRentBike;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonProfile;

    @FXML
    private Button buttonUpdateBikeTable;

    @FXML
    private Button buttonUpdateUserTable;

    @FXML
    private TableColumn<Bicycle, String> configBikeCloseRentBike;

    @FXML
    private TableColumn<Bicycle, String> configBikeRentBike;

    @FXML
    private TableColumn<Bicycle, String> configBikeTable;

    @FXML
    private TableColumn<Bicycle, String> dateBikeCloseRentBike;

    @FXML
    private TableColumn<Bicycle, Integer> idBikeRentBike;

    @FXML
    private TableColumn<Bicycle, Integer> idBikeTable;

    @FXML
    private TableColumn<Shop, String> idShopNameCloseRentBike;

    @FXML
    private TableColumn<ShortUser, Integer> idUserRentBike;

    @FXML
    private TableColumn<User, Integer> idUserTable;

    @FXML
    private TableColumn<Bicycle, String> nameBikeCloseRentBike;

    @FXML
    private TableColumn<Bicycle, String> nameBikeRentBike;

    @FXML
    private TableColumn<Bicycle, String> nameBikeTable;

    @FXML
    private TableColumn<ShortUser, String> nameUserCloseRentBike;

    @FXML
    private TableColumn<ShortUser, String> nameUserRentBike;

    @FXML
    private TableColumn<User, String> nameUserTable;

    @FXML
    private TableColumn<ShortUser, String> passportUserCloseRentBike;

    @FXML
    private TableColumn<ShortUser, String> passportUserRentBike;

    @FXML
    private TableColumn<User, String> passportUserTable;

    @FXML
    private TableColumn<Bicycle, Integer> priceBikeRentBike;

    @FXML
    private TableColumn<Bicycle, Integer> priceBikeTable;

    @FXML
    private Label selectBikeRentBike;

    @FXML
    private Label selectResultCloseRentBike;

    @FXML
    private Label selectUserRentBike;

    @FXML
    private TableColumn<Bicycle, String> shopBikeRentBike;

    @FXML
    private TableColumn<Bicycle, String> statusBikeTable;

    @FXML
    private TableColumn<User, String> statusUserTable;

    @FXML
    private TableView<Bicycle> tableBicycle;
    @FXML
    private Tab closeRentBikeTab;

    @FXML
    private Button buttonUpdateRentBike;

    @FXML
    private Button buttonUpdateCloseRentBike;

    @FXML
    private Tab bikeTableTab;

    @FXML
    private Tab rentBikeTab;

    @FXML
    private Tab userTableTab;


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<User> userTableListUserTable = FXCollections.observableArrayList();
        ObservableList<Bicycle> bikeTableListBikeTable = FXCollections.observableArrayList();
        ObservableList<Bicycle> bikeTableListRentBike = FXCollections.observableArrayList();
        ObservableList<ShortUser> userTableListRentBike = FXCollections.observableArrayList();
        ObservableList<Bicycle> bikeTableListCloseRentBike = FXCollections.observableArrayList();
        ObservableList<ShortUser> userTableListCloseRentBike = FXCollections.observableArrayList();
        ObservableList<Shop> shopsTableListCloseRentBike = FXCollections.observableArrayList();

        idBikeCloseRentBike.setCellValueFactory(new PropertyValueFactory<Bicycle, Integer>("id"));
        nameBikeCloseRentBike.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("name"));
        configBikeCloseRentBike.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("config"));
        dateBikeCloseRentBike.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("additionalInfo"));

        mainTableBikeCloseRentBike.setItems(bikeTableListCloseRentBike);//велосипеды в аренды

        idUserCloseRentBike.setCellValueFactory(new PropertyValueFactory<ShortUser, Integer>("id"));
        nameUserCloseRentBike.setCellValueFactory(new PropertyValueFactory<ShortUser, String>("name"));
        passportUserCloseRentBike.setCellValueFactory(new PropertyValueFactory<ShortUser, String>("passport"));

        mainTableUserCloseRentBike.setItems(userTableListCloseRentBike);//пользователи сдающие велосипеды

        idShopCloseRentBike.setCellValueFactory(new PropertyValueFactory<Shop, Integer>("id"));
        idShopNameCloseRentBike.setCellValueFactory(new PropertyValueFactory<Shop, String>("name"));
        addressShopCloseRentBike.setCellValueFactory(new PropertyValueFactory<Shop, String>("address"));

        mainTableShopCloseRentBike.setItems(shopsTableListCloseRentBike);//магазины в которые можно сдать
        // ------------------------------------
        idUserRentBike.setCellValueFactory(new PropertyValueFactory<ShortUser, Integer>("id"));
        nameUserRentBike.setCellValueFactory(new PropertyValueFactory<ShortUser, String>("name"));
        passportUserRentBike.setCellValueFactory(new PropertyValueFactory<ShortUser, String>("passport"));

        mainTableUserRentBike.setItems(userTableListRentBike);//пользователи берущие в аренды


        idBikeRentBike.setCellValueFactory(new PropertyValueFactory<Bicycle, Integer>("id"));
        nameBikeRentBike.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("name"));
        configBikeRentBike.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("config"));
        shopBikeRentBike.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("additionalInfo"));
        priceBikeRentBike.setCellValueFactory(new PropertyValueFactory<Bicycle, Integer>("price"));

        mainTableBikeRentBike.setItems(bikeTableListRentBike);//велосипеды для аренды

        idBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, Integer>("id"));
        nameBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("name"));
        configBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("config"));
        statusBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("status"));
        priceBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, Integer>("price"));
        additionalInfoBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("additionalInfo"));

        mainTableBike.setItems(bikeTableListBikeTable);//просмотр всех велосипедов
        //------------------------------------

        idUserTable.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nameUserTable.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        passportUserTable.setCellValueFactory(new PropertyValueFactory<User, String>("passport"));
        addressUserTable.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        statusUserTable.setCellValueFactory(new PropertyValueFactory<User, String>("status"));

        mainTableUser.setItems(userTableListUserTable);//просмотр всех пользователей
        //------------------------------------

        updateBikeTable(bikeTableListBikeTable);

        updateUserTable(userTableListUserTable);

        getFreeBikeInfo(bikeTableListRentBike);
        updateShortUserTable(userTableListRentBike);

        updateShopsInfo(shopsTableListCloseRentBike);
        updateShortUserTable(userTableListCloseRentBike);


        buttonUpdateRentBike.setOnAction(event -> {
            updateShortUserTable(userTableListCloseRentBike);
        });
        buttonUpdateBikeTable.setOnAction(event -> {
            updateBikeTable(bikeTableListBikeTable);
        });

        buttonUpdateUserTable.setOnAction(event -> {
            updateUserTable(userTableListUserTable);
        });

        buttonUpdateRentBike.setOnAction(event -> {
            bikeTableListRentBike.clear();
            updateShortUserTable(userTableListRentBike);
            getFreeBikeInfo(bikeTableListRentBike);

        });

        mainTableBikeRentBike.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectBikeRentBike.setText(mainTableBikeRentBike.getSelectionModel().getSelectedItems().get(0).getName() + "(id = " + mainTableBikeRentBike.getSelectionModel().getSelectedItems().get(0).getId() + ")");
            }
        });


        AtomicReference<String> userSelect = new AtomicReference<>("");
        AtomicReference<String> bikeSelect = new AtomicReference<>("");
        AtomicReference<String> shopSelect = new AtomicReference<>("");
        AtomicReference<String> AllSelectedCloseRent = new AtomicReference<>("");

        AtomicReference<ShortUser> userCloseRent = new AtomicReference<>();
        AtomicReference<Bicycle> bikeCloseRent = new AtomicReference<>();
        AtomicReference<Shop> shopCloseRent = new AtomicReference<>();

        mainTableUserCloseRentBike.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                bikeTableListCloseRentBike.clear();
                ShortUser user = mainTableUserCloseRentBike.getSelectionModel().getSelectedItems().get(0);
                userCloseRent.set(user);
                getBikeRentedByUserCloseRentBike(user, bikeTableListCloseRentBike);
                userSelect.set("Пользователь " + user.getName() + "(id = " + user.getId() + ")");
                AllSelectedCloseRent.set(userSelect.get() + bikeSelect.get() + shopSelect.get());
                selectResultCloseRentBike.setText(AllSelectedCloseRent.get());
            } else {
                bikeTableListCloseRentBike.clear();
            }
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

        mainTableBikeCloseRentBike.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Bicycle bike = mainTableBikeCloseRentBike.getSelectionModel().getSelectedItem();
                bikeCloseRent.set(bike);
                bikeSelect.set(" возвращает велосипед " + bike.getName() + "(id = " + bike.getId() + ")");
                AllSelectedCloseRent.set(userSelect.get() + bikeSelect.get() + shopSelect.get());
                selectResultCloseRentBike.setText(AllSelectedCloseRent.get());
            }
        });

        mainTableShopCloseRentBike.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Shop shop = mainTableShopCloseRentBike.getSelectionModel().getSelectedItem();
                shopCloseRent.set(shop);
                shopSelect.set(" в магазин " + shop.getName() + "(id = " + shop.getId() + ")");
                AllSelectedCloseRent.set(userSelect.get() + bikeSelect.get() + shopSelect.get());
                selectResultCloseRentBike.setText(AllSelectedCloseRent.get());
            }
        });

        buttonUpdateCloseRentBike.setOnAction(event -> {
            updateShortUserTable(userTableListCloseRentBike);
            updateShopsInfo(shopsTableListCloseRentBike);
            bikeTableListCloseRentBike.clear();
            shopSelect.set("");
            userSelect.set("");
            bikeSelect.set("");
            AllSelectedCloseRent.set("");

        });

        buttonCloseRentCloseRentBike.setOnAction(event -> {
            if (userCloseRent.get() != null && bikeCloseRent.get() != null && shopCloseRent != null) {
                Alert questionCloseRent = new Alert(Alert.AlertType.INFORMATION, "Вы уверены что все правильно:\n " +
                        "Клиент: " + userCloseRent.get().getName() + " " + userCloseRent.get().getPassport()
                        + "\n Велосипед: " + bikeCloseRent.get().getName() + " " + bikeCloseRent.get().getConfig()
                        + "\n Магазин: " + shopCloseRent.get().getName() + " " + shopCloseRent.get().getAddress(), ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> result = questionCloseRent.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.YES) {
                    ModelEmployeePanel modelEmployeePanel = new ModelEmployeePanel();
                    shopSelect.set("");
                    userSelect.set("");
                    bikeSelect.set("");
                    AllSelectedCloseRent.set("");
                    selectResultCloseRentBike.setText("");
                    if (modelEmployeePanel.createCloseRent(userCloseRent.get(), bikeCloseRent.get(), shopCloseRent.get())) {
                        Alert bikeAdd = new Alert(Alert.AlertType.INFORMATION, "Запись успешно добавлена", ButtonType.OK);
                        bikeAdd.showAndWait();
                    } else {
                        modelEmployeePanel.errorAlert();
                    }
                }
            } else {
                Alert emptyBikeRent = new Alert(Alert.AlertType.ERROR, "Заполните все поля", ButtonType.OK);
            }
        });

        mainTableUserRentBike.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectUserRentBike.setText(mainTableUserRentBike.getSelectionModel().getSelectedItems().get(0).getName() + "(id = " + mainTableUserRentBike.getSelectionModel().getSelectedItems().get(0).getId() + ")");
            }
        });

        buttonAddRentBike.setOnAction(event -> {
            try {
                if ((mainTableBikeRentBike.getSelectionModel().getSelectedItems() != null) && (mainTableUserRentBike.getSelectionModel().getSelectedItems() != null)) {
                    ShortUser user = mainTableUserRentBike.getSelectionModel().getSelectedItem();
                    Bicycle bike = mainTableBikeRentBike.getSelectionModel().getSelectedItem();
                    Alert confirmationAdding = new Alert(Alert.AlertType.INFORMATION, "Вы уверенны, что все верно:\nКлиент: " + user.getName() + " " + user.getPassport() + "\nВелосипед: " + bike.getId() + " " + bike.getName(), ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> result = confirmationAdding.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.YES) {
                        ModelEmployeePanel modelEmployeePanel = new ModelEmployeePanel();

                        if (modelEmployeePanel.createRentBike(bike, user)) {
                            Alert bikeAdd = new Alert(Alert.AlertType.INFORMATION, "Запись успешно добавлена", ButtonType.OK);
                            bikeAdd.showAndWait();

                            selectBikeRentBike.setText("");
                            bikeTableListRentBike.clear();
                            updateShortUserTable(userTableListRentBike);
                            getFreeBikeInfo(bikeTableListRentBike);
                        } else {
                            modelEmployeePanel.errorAlert();
                        }

                    }
                } else {
                    Alert emptyBikeRent = new Alert(Alert.AlertType.ERROR, "Выберите велосипед для аренды", ButtonType.OK);
                    emptyBikeRent.showAndWait();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        buttonExit.setOnAction(event -> {
            buttonExit.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            try {
                String title;
                if (!(ModelEmployeePanel.getMandat().equals("super_user"))) {
                    loader.setLocation(getClass().getResource("/com/example/demo4/Viev/EntryWindow.fxml"));
                    title = "Авторизация";
                }
                else{
                    loader.setLocation(getClass().getResource("/com/example/demo4/Viev/AdminPanel.fxml"));
                    title = "Панель администратора";
                }

                loader.load();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle(title);
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void getBikeRentedByUserCloseRentBike(ShortUser user,ObservableList<Bicycle> bikeTableListCloseRentBike) {
        try {
            ModelEmployeePanel modelEmployeePanel = new ModelEmployeePanel();
            ResultSet rentBikeInfo = modelEmployeePanel.getBikeRentedByUserCloseRentBike(user);
            bikeTableListCloseRentBike.clear();
            while (rentBikeInfo.next()) {
                bikeTableListCloseRentBike.add(new Bicycle(
                        rentBikeInfo.getInt(BIKE_ID),
                        rentBikeInfo.getString(BIKE_NAME),
                        "Модель: " + rentBikeInfo.getString(BIKE_MODEL) + "\nТип: " + rentBikeInfo.getString(BIKE_TYPE) + "\nКол-во передач: " + rentBikeInfo.getInt(BIKE_NUMBERGEAR),
                        rentBikeInfo.getString(BIKE_STATUS),
                        rentBikeInfo.getInt(BIKE_PRICEHOUR),
                        rentBikeInfo.getString(RESERVATION_DATERECEIPT)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private boolean createCloseRent(ShortUser user, Bicycle bike, Shop shop){
        ModelEmployeePanel modelEmployeePanel = new ModelEmployeePanel();
        return modelEmployeePanel.createCloseRent(user, bike, shop);
    }
    private void updateShopsInfo(ObservableList<Shop> shopsTableListCloseRentBike) {
        try {
            ModelEmployeePanel modelEmployeePanel = new ModelEmployeePanel();
            ResultSet shopsInfo = modelEmployeePanel.getShopsInfo();
            while (shopsInfo.next()) {
                shopsTableListCloseRentBike.add(new Shop(
                        shopsInfo.getInt(SHOPS_ID),
                        shopsInfo.getString(SHOPS_NAME),
                        shopsInfo.getString(SHOPS_ADDRESS)
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateBikeTable(ObservableList<Bicycle> bikeTableList) {
        bikeTableList.clear();
        getFreeBikeInfo(bikeTableList);
        getBusyBikeInfo(bikeTableList);

    }

    public void updateUserTable(ObservableList<User> userTableList) {
        try {
            userTableList.clear();
            ModelEmployeePanel modelEmployeePanel = new ModelEmployeePanel();
            ResultSet userInfo = modelEmployeePanel.getUserInfo();
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

    public void updateShortUserTable(ObservableList<ShortUser> userTableList) {
        try {
            userTableList.clear();
            ModelEmployeePanel modelEmployeePanel = new ModelEmployeePanel();
            ResultSet userInfo = modelEmployeePanel.getShortUserInfo();
            while (userInfo.next()) {
                userTableList.add(new ShortUser(
                        userInfo.getInt(USER_ID),
                        userInfo.getString(USER_NAME),
                        userInfo.getString(PASSPORT_SERIES) + " " + userInfo.getString(PASSPORT_NUMBER)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getBusyBikeInfo(ObservableList<Bicycle> bikeTableList) {
        try {
            ModelEmployeePanel modelEmployeePanel = new ModelEmployeePanel();
            ResultSet bikeInfo = modelEmployeePanel.getBusyBikeInfo();
            while (bikeInfo.next()) {
                bikeTableList.add(new Bicycle(bikeInfo.getInt(BIKE_ID),
                        bikeInfo.getString(BIKE_NAME),
                        "Модель: " + bikeInfo.getString(BIKE_MODEL) + "\nТип: " + bikeInfo.getString(BIKE_TYPE) + "\nКол-во передач: " + bikeInfo.getInt(BIKE_NUMBERGEAR),
                        bikeInfo.getString(BIKE_STATUS),
                        bikeInfo.getInt(BIKE_PRICEHOUR),
                        bikeInfo.getString("MAX(" + RESERVATION_TABLE + "." + RESERVATION_DATERECEIPT + ")")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getFreeBikeInfo(ObservableList<Bicycle> bikeTableList) {
        try {
            ModelEmployeePanel modelEmployeePanel = new ModelEmployeePanel();
            ResultSet bikeInfo = modelEmployeePanel.getBusyBikeInfo();
            bikeInfo = modelEmployeePanel.getFreeBikeInfo();
            while (bikeInfo.next()) {
                bikeTableList.add(new Bicycle(bikeInfo.getInt(BIKE_ID),
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
