module com.example.tipcalculatormodification {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tipcalculatormodification to javafx.fxml;
    exports com.example.tipcalculatormodification;
}