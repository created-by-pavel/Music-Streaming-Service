package ru.crbpavel.musicapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Trixy");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}