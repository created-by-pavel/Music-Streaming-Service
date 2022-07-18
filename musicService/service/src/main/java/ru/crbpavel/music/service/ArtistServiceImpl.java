package ru.crbpavel.music.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.crbpavel.music.model.Artist;
import ru.crbpavel.music.repository.ArtistRepository;
import ru.crbpavel.music.tool.MusicServiceException;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    ArtistRepository repository;

    public Artist save(Artist artist) {
        return repository.save(artist);
    }

    public Artist getById(Long id) {
        return repository.getById(id);
    }

    public Artist findByName(String name) {
        return repository.findByName(name);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void updateArtist(Artist artist) {
        Long id = artist.getId();
        Artist existingArtist = repository.findById(id).orElse(null);
        if (existingArtist == null) throw new MusicServiceException("cant find user");
        //existingArtist.setAlbums(artist.getAlbums());
        existingArtist.setImageFilePath(artist.getImageFilePath());
        repository.save(existingArtist);
    }
}
