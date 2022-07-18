package ru.crbpavel.music.service;

import ru.crbpavel.music.model.Album;
import ru.crbpavel.music.model.Song;
import ru.crbpavel.music.model.User;

import java.util.Set;

public interface UserService {
    User save(User user);

    User getById(Long id);

    Set<Song> getAllSongs(String username);

    Set<Album> getAllAlbums(String username);

    void delete(String userName);

    void addSong(Song song, String username);

    void addAlbum(Album album, String username);

    String findUserBySecurityModel(String username);

    void deleteSong(Song song, String username);

    void updateUser(User user, String username);
}
