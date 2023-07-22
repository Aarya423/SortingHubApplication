package com.example.apate695_assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SortingHubApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SortingHubApplication.class.getResource("SortingHub-view.fxml"));
        //changed size, title, and added a western logo
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Sorting Hub");
        stage.getIcons().add(new Image("file:src/main/resources/com/example/apate695_assignment1/WesternLogo.png"));

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}