package ru.crbpavel.music.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.crbpavel.music.model.Song;
import ru.crbpavel.music.repository.SongRepository;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongRepository repository;

    public Song save(Song song) {
        return repository.save(song);
    }

    public List<Song> getAll() {
        return repository.findAll();
    }

    public Song getById(Long id) {
        return repository.getById(id);
    }

    public Song findByName(String name) {
        return repository.findByName(name);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
