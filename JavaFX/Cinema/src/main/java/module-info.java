module com.example.cinema {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cinema to javafx.fxml;
    exports com.example.cinema;
}