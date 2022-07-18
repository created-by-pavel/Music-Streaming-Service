package ru.crbpavel.musicapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.crbpavel.musicapp.model.Context;
import ru.crbpavel.musicapp.model.DrawCover;
import ru.crbpavel.musicapp.model.Song;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SongController implements Initializable {
    @FXML
    private Pane coverPane;

    @FXML
    private Label songName;

    @FXML
    private Label artist;

    @FXML
    private Button songPlayButton;

    @FXML
    private Button songMoreButton;

    @FXML
    private VBox vBox;

    @FXML
    private ImageView songImg;

    @FXML
    private ImageView buttonImg;

    @FXML
    private ImageView moreButtonImg;

    private Song song;

    public void setData(Song song) {
        Image img = new Image(new File(song.getImgFilePath()).toURI().toString());
        DrawCover.setCover(img, songImg);
        songName.setText(song.getName());
        artist.setText(song.getArtist().getName());
        this.song = song;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DrawCover.draw(vBox, songPlayButton, songMoreButton, coverPane, buttonImg, moreButtonImg);
    }

    @FXML
    public void playSong(ActionEvent event) {
        PlayBarController playBarController = (PlayBarController) Context.getController();
        playBarController.setSong(song);
    }
}
