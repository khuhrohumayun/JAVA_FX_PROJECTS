module com.example.loginregister {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.loginregister to javafx.fxml;
    exports com.example.loginregister;
}