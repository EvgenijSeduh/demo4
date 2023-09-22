module com.example.demo4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo4 to javafx.fxml;
    exports com.example.demo4;
    exports com.example.demo4.Controler;
    opens com.example.demo4.Controler to javafx.fxml;
    exports com.example.demo4.Viev;
    opens com.example.demo4.Viev to javafx.fxml;
    exports com.example.demo4.Recource;
    opens com.example.demo4.Recource to javafx.fxml;
//    exports com;
//    opens com to javafx.fxml;
}