module com.example.demo4 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.demo4 to javafx.fxml;
    exports com.example.demo4;
    exports com.example.demo4.Controler;
    opens com.example.demo4.Controler to javafx.fxml;
}