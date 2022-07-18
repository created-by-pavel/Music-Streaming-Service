package ru.crbpavel.music.service;

import ru.crbpavel.music.model.PlayList;
import ru.crbpavel.music.model.Song;

import java.util.List;

public interface PlayListService {
    void save(PlayList playList);

    List<PlayList> getAll(String username);

    PlayList getById(Long id, String username);

    PlayList findByName(String name, String username);

    void deleteById(Long id, String username);

    void updatePlayList(PlayList playList, String username);

    void addSongById(Long id, Song song, String username);

}
