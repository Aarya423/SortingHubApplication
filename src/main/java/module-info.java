module com.example.apate695_assignment1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.apate695_assignment1 to javafx.fxml;
    exports com.example.apate695_assignment1;
}