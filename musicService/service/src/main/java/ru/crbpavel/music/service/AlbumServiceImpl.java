package ru.crbpavel.music.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.crbpavel.music.model.Album;
import ru.crbpavel.music.model.Song;
import ru.crbpavel.music.repository.AlbumRepository;
import ru.crbpavel.music.tool.MusicServiceException;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository repository;

    public Album save(Album album) {
        return repository.save(album);
    }

    public List<Album> getAll() {
        return repository.findAll();
    }

    public Album getById(Long id) {
        return repository.getById(id);
    }

    public Album findByTitle(String name) {
        return repository.findByTitle(name);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void updateAlbum(Album album) {
        Long id = album.getId();
        Album existingAlbum = repository.findById(id).orElse(null);
        if (existingAlbum == null) throw new MusicServiceException("cant find user");
        existingAlbum.setTitle(album.getTitle());
        existingAlbum.setArtist(album.getArtist());
        existingAlbum.setGenre(album.getGenre());
        existingAlbum.setSongs(album.getSongs());
        existingAlbum.setImageFilePath(album.getImageFilePath());
        existingAlbum.setReleaseDate(album.getReleaseDate());
        repository.save(existingAlbum);
    }

    public void setSongsById(Long id, List<Song> songs) {
        Album existingAlbum = repository.findById(id).orElse(null);
        if (existingAlbum == null) throw new MusicServiceException("cant find user");
        existingAlbum.setSongs(songs);
        repository.save(existingAlbum);
    }
}
