package ru.crbpavel.musicapp;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import ru.crbpavel.musicapp.model.Context;
import ru.crbpavel.musicapp.model.Song;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlayBarController implements Initializable {
    @FXML
    private Label songName;

    @FXML
    private Label songArtist;

    @FXML
    private ImageView songImg;

    @FXML
    private Label currentTimeLabel;

    @FXML
    private Label totalTime;

    @FXML
    private Button playButton;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider progressSlider;

    @FXML
    private Button likeButton;

    private boolean isPlaying = false;
    private boolean isLiked = false;
    private boolean atEndOfSong = false;
    private ImageView ivPlay;
    private ImageView ivPause;
    private ImageView ivDislike;
    private ImageView ivLike;
    private Media media;
    private MediaPlayer mediaPlayer;
    private int index;
    private List<Song> songs = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Context.getInstance().setController(this);
        Image imagePlay = new Image(new File("/Users/pavel/IdeaProjects/musicProject/musicApp/src/main/resources/ru/crbpavel/musicapp/img/playBtn.png").toURI().toString());
        Image imagePause = new Image(new File("/Users/pavel/IdeaProjects/musicProject/musicApp/src/main/resources/ru/crbpavel/musicapp/img/pause.png").toURI().toString());
        Image imageDislike = new Image(new File("/Users/pavel/IdeaProjects/musicProject/musicApp/src/main/resources/ru/crbpavel/musicapp/img/ic_love.png").toURI().toString());
        Image imageLike = new Image(new File("/Users/pavel/IdeaProjects/musicProject/musicApp/src/main/resources/ru/crbpavel/musicapp/img/ic_love.png").toURI().toString());

        ivPlay = new ImageView(imagePlay);
        int IV_SIZE = 40;
        ivPlay.setFitHeight(IV_SIZE);
        ivPlay.setFitWidth(IV_SIZE);

        ivPause = new ImageView(imagePause);
        ivPause.setFitWidth(IV_SIZE);
        ivPause.setFitHeight(IV_SIZE);

        ivDislike = new ImageView(imageDislike);
        ivDislike.setFitWidth(22);
        ivDislike.setFitHeight(23);

        ivLike = new ImageView(imageLike);
        ivLike.setFitWidth(22);
        ivLike.setFitHeight(23);

        playButton.setGraphic(ivPlay);
        likeButton.setGraphic(ivDislike);

        playButton.setOnAction(actionEvent -> {
            Button buttonPlay = (Button) actionEvent.getSource();
            bindCurrentTimeLabel();
            if (atEndOfSong) {
                progressSlider.setValue(0);
                atEndOfSong = false;
                isPlaying = false;
            }
            if (isPlaying) {
                buttonPlay.setGraphic(ivPlay);
                mediaPlayer.pause();
                isPlaying = false;
            } else {
                buttonPlay.setGraphic(ivPause);
                mediaPlayer.play();
                isPlaying = true;
            }
        });

        likeButton.setOnAction(actionEvent -> {
            Button likeButton = (Button) actionEvent.getSource();
            if (isLiked) {
                likeButton.setGraphic(ivDislike);
                isLiked = false;
            } else {
                likeButton.setGraphic(ivLike);
                isLiked = true;
            }
        });

    }

    public void bindCurrentTimeLabel() {
        currentTimeLabel.textProperty().bind(Bindings.createStringBinding(() ->
                getTime(mediaPlayer.getCurrentTime()), mediaPlayer.currentTimeProperty()));
    }

    public String getTime(Duration time) {
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if (seconds > 59) seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void labelsMatchEndVideo(String labelTime, String labelTotalTime) {
        for (int i = 0; i < labelTotalTime.length(); i++) {
            if (labelTime.charAt(i) != labelTotalTime.charAt(i)) {
                atEndOfSong = false;
                if (isPlaying) playButton.setGraphic(ivPause);
                else playButton.setGraphic(ivPlay);
                break;
            } else {
                atEndOfSong = true;
                playButton.setGraphic(ivPause);
            }
        }
    }

    public void setSong(Song song) {
        index = 0;
        songs.add(song);
        playSongs();
    }

    public void setAlbum(List<Song> songs) {
        index = 0;
        this.songs = songs;
        playSongs();
    }

    @FXML
    public void nextSong(ActionEvent event) {
        if (index < songs.size() - 1) {
            index++;
        } else {
            index = 0;
        }
        mediaPlayer.stop();
        isPlaying = false;
        playSongs();
    }

    @FXML
    public void previousSong(ActionEvent event) {
        if (index > 0) {
            index--;
        } else {
            index = songs.size() - 1;
        }
        mediaPlayer.stop();
        isPlaying = false;
        playSongs();
    }

    private void playSongs() {
        songImg.setImage(new Image(new File(songs.get(index).getImgFilePath()).toURI().toString()));
        songName.setText(songs.get(index).getName());
        songArtist.setText(songs.get(index).getArtist().getName());
        media = new Media(new File(songs.get(index).getSongFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.volumeProperty().bindBidirectional(volumeSlider.valueProperty());
        volumeSlider.valueProperty().addListener(observable ->
                mediaPlayer.setVolume(volumeSlider.getValue()));

        mediaPlayer.totalDurationProperty().addListener((observableValue, oldDuration, newDuration) -> {
            progressSlider.setMax(newDuration.toSeconds());
            totalTime.setText(getTime(newDuration));
        });

        progressSlider.valueChangingProperty().addListener((observableValue, wasChanging, isChanging) -> {
            bindCurrentTimeLabel();
            if (!isChanging) {
                mediaPlayer.seek(Duration.seconds(progressSlider.getValue()));
            }
        });

        progressSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            bindCurrentTimeLabel();
            double currentTime = mediaPlayer.getCurrentTime().toSeconds();
            if (Math.abs(currentTime - newValue.doubleValue()) > 0.5) {
                mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
            }
            labelsMatchEndVideo(currentTimeLabel.getText(), totalTime.getText());
        });

        mediaPlayer.currentTimeProperty().addListener((observableValue, oldTime, newTime) -> {
            bindCurrentTimeLabel();
            if (!progressSlider.isValueChanging()) {
                progressSlider.setValue(newTime.toSeconds());
            }
            labelsMatchEndVideo(currentTimeLabel.getText(), totalTime.getText());
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            playButton.setGraphic(ivPlay);
            atEndOfSong = true;
            if (!currentTimeLabel.textProperty().equals(totalTime.textProperty())) {
                currentTimeLabel.textProperty().unbind();
                currentTimeLabel.setText(getTime(mediaPlayer.getTotalDuration()));
            }
        });

        mediaPlayer.play();
        isPlaying = true;
        playButton.setGraphic(ivPause);
        likeButton.setVisible(true);
    }
}
