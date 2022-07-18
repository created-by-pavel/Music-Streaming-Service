package ru.crbpavel.musicapp.model;

import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class DrawCover {

    public static void setCover(Image img, ImageView albumImage) {
        albumImage.setImage(img);
        Rectangle clip = new Rectangle(
                albumImage.getFitWidth(), albumImage.getFitHeight()
        );
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        albumImage.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = albumImage.snapshot(parameters, null);
        albumImage.setClip(null);
        albumImage.setImage(image);
    }

    public static void draw(VBox vBox, Button playButton, Button moreButton, Pane coverPane, ImageView buttonImg, ImageView moreButtonImg) {
        vBox.setOnMouseEntered(event -> {
            playButton.setVisible(true);
            moreButton.setVisible(true);
            var colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.25);
            colorAdjust.setContrast(-.035);
            coverPane.setEffect(colorAdjust);
            var colorAdjust2 = new ColorAdjust();
            playButton.setEffect(colorAdjust2);
            moreButton.setEffect(colorAdjust2);
        });

        vBox.setOnMouseExited(event -> {
            coverPane.setEffect(null);
            playButton.setVisible(false);
            moreButton.setVisible(false);
        });

        playButton.setOnMouseEntered(event -> {
            Image img = new Image(new File("/Users/pavel/IdeaProjects/musicProject/musicApp/src/main/resources/ru/crbpavel/musicapp/img/norm_logo.png").toURI().toString());
            buttonImg.setImage(img);
        });

        playButton.setOnMouseExited(event -> {
            Image img = new Image(new File("/Users/pavel/IdeaProjects/musicProject/musicApp/src/main/resources/ru/crbpavel/musicapp/img/song_play_ico.png").toURI().toString());
            buttonImg.setImage(img);
        });

        moreButton.setOnMouseEntered(event -> {
            Image img = new Image(new File("/Users/pavel/IdeaProjects/musicProject/musicApp/src/main/resources/ru/crbpavel/musicapp/img/norm_logo2.png").toURI().toString());
            moreButtonImg.setImage(img);
        });

        moreButton.setOnMouseExited(event -> {
            Image img = new Image(new File("/Users/pavel/IdeaProjects/musicProject/musicApp/src/main/resources/ru/crbpavel/musicapp/img/more_logo_standart.png").toURI().toString());
            moreButtonImg.setImage(img);
        });
    }
}
