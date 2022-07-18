package ru.crbpavel.music.service;

import ru.crbpavel.music.model.Artist;

public interface ArtistService {
    Artist save(Artist artist);

    Artist getById(Long id);

    Artist findByName(String name);

    void deleteById(Long id);

    void updateArtist(Artist artist);
}
