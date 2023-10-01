package com.example.demo4.Controler;

import com.example.demo4.Model.AdminPanelModel;
import com.example.demo4.Model.ModelEmployeePanel;
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
import java.util.Optional;

public class AdminPanelController extends ConstAllTable {

    @FXML
    private TableColumn<Bicycle, String> additionalInfoBikeTable;

    @FXML
    private TableColumn<User, String> addressUser;

    @FXML
    private Button buttonAddBike;

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
    private Button buttonUpdateBikeAddBike;

    @FXML
    private Button buttonDeleteBikeTable;

    @FXML
    private Button buttonUpdateUser;

    @FXML
    private TableColumn<Bicycle, String> configBikeTable;

    @FXML
    private TableColumn<Bicycle, Integer> idBikeTable;

    @FXML
    private TableColumn<User, Integer> idUser;

    @FXML
    private TableView<Bicycle> mainTableBike;

    @FXML
    private TableView<User> mainTableUser;

    @FXML
    private TextField modelFromAdd;

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
    private ComboBox<Shop> shopListFromAdd;

    @FXML
    private TableColumn<Bicycle, String> statusBikeTable;

    @FXML
    private TableColumn<User, String> statusUser;

    @FXML
    private TextField typeFromAdd;


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

        idBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, Integer>("id"));
        nameBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("name"));
        configBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("config"));
        statusBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("status"));
        priceBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, Integer>("price"));
        additionalInfoBikeTable.setCellValueFactory(new PropertyValueFactory<Bicycle, String>("additionalInfo"));
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

                try {
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
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Alert emptyField = new Alert(Alert.AlertType.ERROR, "Заполните все поля", ButtonType.OK);
                emptyField.showAndWait();
            }
        });

        buttonUpdateBikeAddBike.setOnAction(event -> {
            updateBikeTable(bikeList);
        });

        buttonDeleteBikeTable.setOnAction(event -> {
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

        buttonRaiseStatusUser.setOnAction(event -> {
            User user = mainTableUser.getSelectionModel().selectedItemProperty().get();
            if (user != null) {
                AdminPanelModel adminPanelModel = null;
                try {
                    adminPanelModel = new AdminPanelModel();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                adminPanelModel.raiseStatusUser(user.getStatus(), user.getId());
                updateUserTable(userTableListUserTable);
            }
        });

        buttonDeleteUser.setOnAction(event -> {
            User user = mainTableUser.getSelectionModel().selectedItemProperty().get();
            Alert confirmationDeletion = new Alert(Alert.AlertType.WARNING, "Вы уверенены?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmationDeletion.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {

                try {
                    if (deleteUser(user)) {
                        Alert doneDelete = new Alert(Alert.AlertType.INFORMATION, "Пользователь удален", ButtonType.OK);
                        doneDelete.showAndWait();
                    } else {
                        Alert errorDelete = new Alert(Alert.AlertType.ERROR, "Пользователь не был удален", ButtonType.OK);
                        errorDelete.showAndWait();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        buttonDeleteBikeTable.setOnAction(event -> {
            Bicycle bike = mainTableBike.getSelectionModel().selectedItemProperty().get();
            Alert confirmationDeletion = new Alert(Alert.AlertType.WARNING, "Вы уверенены?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmationDeletion.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                try {
                    if (deleteBike(bike)) {
                        Alert doneDelete = new Alert(Alert.AlertType.INFORMATION, "Велосипед удален", ButtonType.OK);
                        doneDelete.showAndWait();
                    } else {
                        Alert errorDelete = new Alert(Alert.AlertType.ERROR, "Велосипед не был удален", ButtonType.OK);
                        errorDelete.showAndWait();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        buttonUpdateUser.setOnAction(event -> {
            updateUserTable(userTableListUserTable);
        });
    }

    private boolean deleteUser(User user) throws SQLException, ClassNotFoundException {
        AdminPanelModel adminPanelModel = new AdminPanelModel();
        if(ModelEmployeePanel.getMandat().equals("super_user"))
            return adminPanelModel.deleteUser(user);
        else{
            Alert errorDelete = new Alert(Alert.AlertType.ERROR, "Вы не можете удалить администратора", ButtonType.OK);
            errorDelete.showAndWait();
            return false;
        }
    }

    private boolean deleteBike(Bicycle bike) throws SQLException, ClassNotFoundException {
        AdminPanelModel adminPanelModel = new AdminPanelModel();
        return adminPanelModel.deleteBike(bike);
    }

    private boolean addBike(String name, String type, String model, String numbergear, String price, int idshops) throws SQLException, ClassNotFoundException {
        AdminPanelModel adminPanelController = new AdminPanelModel();
        return adminPanelController.addBike(name, type, model, numbergear, price, idshops);
    }

    private void getAllShops(ObservableList<Shop> allShopsList) throws SQLException, ClassNotFoundException {
        AdminPanelModel adminPanelController = new AdminPanelModel();
        ResultSet allShopRes = adminPanelController.getAllShops();

        while (allShopRes.next()) {
            allShopsList.add(new Shop(
                    allShopRes.getInt(SHOPS_ID),
                    allShopRes.getString(SHOPS_NAME),
                    allShopRes.getString(SHOPS_ADDRESS)
            ));
        }
    }

    public void updateBikeTable(ObservableList list) {
        try {
            list.clear();
            AdminPanelModel adminPanelModel = new AdminPanelModel();
            ResultSet bikeInfo = adminPanelModel.getBusyBikeInfo();
            while (bikeInfo.next()) {
                list.add(new Bicycle(bikeInfo.getInt(BIKE_ID),
                        bikeInfo.getString(BIKE_NAME),
                        "Модель: " + bikeInfo.getString(BIKE_MODEL) + "\nТип: " + bikeInfo.getString(BIKE_TYPE) + "\nКол-во передач: " + bikeInfo.getInt(BIKE_NUMBERGEAR),
                        bikeInfo.getString(BIKE_STATUS),
                        bikeInfo.getInt(BIKE_PRICEHOUR),
                        bikeInfo.getString("MAX(" + RESERVATION_TABLE + "." + RESERVATION_DATERECEIPT + ")")));
                System.out.println("Модель: " + bikeInfo.getString(BIKE_MODEL) + "\nТип: " + bikeInfo.getString(BIKE_TYPE) + "\nКол-во передач: " + bikeInfo.getInt(BIKE_NUMBERGEAR));
            }

            bikeInfo = adminPanelModel.getFreeBikeInfo();
            while (bikeInfo.next()) {
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
