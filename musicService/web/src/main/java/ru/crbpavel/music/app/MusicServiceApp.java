package ru.crbpavel.music.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.crbpavel.music.enums.Role;
import ru.crbpavel.music.model.*;
import ru.crbpavel.music.service.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MusicServiceApp {

    AppConfig config = new AppConfig();

    public static void main(String[] args) {
        SpringApplication.run(MusicServiceApp.class);
    }

    @Bean
    CommandLineRunner run(UserService userService,
                          SecurityModelService securityModelService,
                          SongService songService,
                          ArtistService artistService,
                          AlbumService albumService) {
        return args -> {
            SecurityModel securityModel = securityModelService.save(new SecurityModel(1L, "yoda133@mail.ru", "1234", Role.USER));
            User user = userService.save(new User(1L, securityModel, "pavel", null, null));

            Artist drake = artistService.save(new Artist(1L, "Drake"));
            Album scaryHours2 = albumService.save(new Album(1L,
                    drake,
                    null,
                    "Scary Hours 2",
                    "/Users/pavel/Desktop/song_covers/wants_and_needs.jpg",
                    null,
                    null));
            Song song1 = songService.save(new Song(
                    1L,
                    "Wants and Needs",
                    "/Users/pavel/Desktop/wants and needs Drake.wav",
                    null,
                    "/Users/pavel/Desktop/song_covers/wants_and_needs.jpg",
                    drake,
                    0));

            Artist eminem = artistService.save(new Artist(2L, "Eminem"));
            Album theEminemShow = albumService.save(new Album(2L,
                    eminem,
                    null,
                    "The Eminem show",
                    "/Users/pavel/Desktop/song_covers/without_me.jpeg",
                    null,
                    null));
            Song song2 = songService.save(new Song(
                    2L,
                    "without me",
                    "/Users/pavel/Desktop/Eminem - Lose Your self.mp3",
                    null,
                    "/Users/pavel/Desktop/song_covers/without_me.jpeg",
                    eminem,
                    0));

            Artist jucieWRLD = artistService.save(new Artist(3L, "Jucie WRLD"));
            Album legends_never_die = albumService.save(new Album(3L,
                    jucieWRLD,
                    null,
                    "Legends Never Die",
                    "/Users/pavel/Desktop/song_covers/conversation.jpg",
                    null,
                    null));
            Song song3 = songService.save(new Song(
                    3L,
                    "Conversation",
                    "/Users/pavel/Desktop/Juice_WRLD_Conversations.mp3",
                    null,
                    "/Users/pavel/Desktop/song_covers/conversation.jpg",
                    jucieWRLD,
                    0));

            Album Curtain_call_the_hits = albumService.save(new Album(4L,
                    eminem,
                    null,
                    "Curtain Call: The Hits",
                    "/Users/pavel/Desktop/song_covers/loseyourself.jpg",
                    null,
                    null));
            Artist jCole = artistService.save(new Artist(4L, "J. Cole"));
            Song song4 = songService.save(new Song(
                    4L,
                    "No Role Modelz",
                    "/Users/pavel/Desktop/loose yourself.wav",
                    null,
                    "/Users/pavel/Desktop/song_covers/no_role_modelz.jpeg",
                    jCole,
                    0));
            Artist kidCudi = artistService.save(new Artist(5L, "Kid Cudi"));
            Song song5 = songService.save(new Song(
                    5L,
                    "Up Up & away",
                    "/Users/pavel/Desktop/loose yourself.wav",
                    null,
                    "/Users/pavel/Desktop/song_covers/kid_cudi.jpg",
                    kidCudi,
                    0));
            Artist rickAstley = artistService.save(new Artist(6L, "Rick Astley"));
            Song song6 = songService.save(new Song(
                    6L,
                    "Never Gonna Give You Up",
                    "/Users/pavel/Desktop/loose yourself.wav",
                    null,
                    "/Users/pavel/Desktop/song_covers/never_gonna.jpeg",
                    rickAstley,
                    0));
            List<Song> songs = new ArrayList<>();
            songs.add(song1);
            songs.add(song2);
            songs.add(song3);
            scaryHours2.setSongs(songs);

            List<Song> songs2 = new ArrayList<>();
            songs2.add(song4);
            songs2.add(song5);
            songs2.add(song6);
            theEminemShow.setSongs(songs2);
            userService.addSong(song1, "yoda133@mail.ru");
            userService.addSong(song2, "yoda133@mail.ru");
            userService.addSong(song3, "yoda133@mail.ru");
            userService.addSong(song4, "yoda133@mail.ru");
            userService.addSong(song5, "yoda133@mail.ru");
            userService.addSong(song6, "yoda133@mail.ru");
            userService.addAlbum(scaryHours2, "yoda133@mail.ru");
            userService.addAlbum(theEminemShow, "yoda133@mail.ru");
            userService.addAlbum(legends_never_die, "yoda133@mail.ru");
            userService.addAlbum(Curtain_call_the_hits, "yoda133@mail.ru");
        };
    }
}
