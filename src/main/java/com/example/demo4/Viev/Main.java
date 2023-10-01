package com.example.demo4.Viev;

import com.example.demo4.Recource.DataBaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EntryWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 180);
        stage.setTitle("Авторизация");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        DataBaseHandler.getInstance();
    }

    public static void main(String[] args) {
        launch();
    }
}