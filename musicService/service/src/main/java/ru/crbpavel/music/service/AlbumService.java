package ru.crbpavel.music.service;

import ru.crbpavel.music.model.Album;
import ru.crbpavel.music.model.Song;

import java.util.List;

public interface AlbumService {
    Album save(Album album);

    List<Album> getAll();

    Album getById(Long id);

    Album findByTitle(String name);

    void deleteById(Long id);

    void updateAlbum(Album album);

    void setSongsById(Long id, List<Song> songs);
}
