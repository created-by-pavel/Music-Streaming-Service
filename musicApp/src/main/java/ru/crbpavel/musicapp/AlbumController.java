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
import ru.crbpavel.musicapp.model.Album;
import ru.crbpavel.musicapp.model.Context;
import ru.crbpavel.musicapp.model.DrawCover;
import ru.crbpavel.musicapp.model.Song;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AlbumController implements Initializable {
    @FXML
    private ImageView albumImage;

    @FXML
    private Button albumMoreButton;

    @FXML
    private Label albumName;

    @FXML
    private VBox albumVBox;

    @FXML
    private Label artist;

    @FXML
    private ImageView buttonImg;

    @FXML
    private ImageView moreButtonImg;

    @FXML
    private Button albumPlayButton;

    @FXML
    private Pane coverPane;

    private List<Song> songs;

    public void setData(Album album) {
        Image img = new Image(new File(album.getImageFilePath()).toURI().toString());
        DrawCover.setCover(img, albumImage);
        albumName.setText(album.getTitle());
        artist.setText(album.getArtist().getName());
        songs = album.getSongs();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DrawCover.draw(albumVBox, albumPlayButton, albumMoreButton, coverPane, buttonImg, moreButtonImg);
    }

    @FXML
    public void playAlbum(ActionEvent event) {
        PlayBarController playBarController = (PlayBarController) Context.getController();
        playBarController.setAlbum(songs);
    }
}
