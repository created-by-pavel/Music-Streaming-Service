package ru.crbpavel.music.service;

import ru.crbpavel.music.model.Song;

import java.util.List;

public interface SongService {
    Song save(Song song);

    List<Song> getAll();

    Song getById(Long id);

    Song findByName(String name);

    void deleteById(Long id);
}
