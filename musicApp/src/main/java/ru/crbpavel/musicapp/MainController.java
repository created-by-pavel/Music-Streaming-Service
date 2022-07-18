package ru.crbpavel.musicapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.crbpavel.musicapp.model.Album;
import ru.crbpavel.musicapp.model.SecurityModel;
import ru.crbpavel.musicapp.model.Song;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String GET_SONGS_URL = "http://localhost:9194/user/get-all-songs";
    private final String GET_ALBUMS_URL = "http://localhost:9194/user/get-all-albums";
    @FXML
    private HBox addedSongsContainer;
    @FXML
    private HBox recommendationContainer;
    @FXML
    private HBox albumContainer;
    @FXML
    private HBox playBarContainer;
    @FXML
    private Label profile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Song> addedSongs = getAddedSongs();
        List<Album> albums = getAlbums();
        try {
            for (Song song : addedSongs) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("song.fxml"));
                VBox vBox = fxmlLoader.load();
                SongController songController = fxmlLoader.getController();
                songController.setData(song);
                addedSongsContainer.getChildren().add(vBox);
            }

            for (Album album : albums) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("album.fxml"));
                VBox vBox = fxmlLoader.load();
                AlbumController albumController = fxmlLoader.getController();
                albumController.setData(album);
                albumContainer.getChildren().add(vBox);
            }

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("playBar.fxml"));
            HBox hbox = fxmlLoader.load();
            playBarContainer.getChildren().add(hbox);


            profile.setText(SecurityModel.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Song> getAddedSongs() {
        HttpHeaders authenticationHeaders = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(authenticationHeaders);
        ResponseEntity<Song[]> responseEntity = restTemplate.exchange(GET_SONGS_URL,
                HttpMethod.GET, entity, Song[].class);
        Song[] songs = responseEntity.getBody();
        if (songs != null) {
            return Arrays.stream(songs).toList();
        } else return null;
    }

    private List<Album> getAlbums() {
        HttpHeaders authenticationHeaders = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(authenticationHeaders);
        ResponseEntity<Album[]> responseEntity = restTemplate.exchange(GET_ALBUMS_URL,
                HttpMethod.GET, entity, Album[].class);
        Album[] albums = responseEntity.getBody();
        if (albums != null) {
            return Arrays.stream(albums).toList();
        } else return null;
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.setBearerAuth(SecurityModel.getAccessToken());
        headers.setConnection("keep-alive");
        return headers;
    }
}