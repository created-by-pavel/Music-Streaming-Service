module ru.crbpavel.musicapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires spring.core;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires javafx.media;


    opens ru.crbpavel.musicapp to javafx.fxml;
    exports ru.crbpavel.musicapp;
    exports ru.crbpavel.musicapp.model to com.fasterxml.jackson.databind;
}