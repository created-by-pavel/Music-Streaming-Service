package ru.crbpavel.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.crbpavel.music.model.Song;
import ru.crbpavel.music.service.SongService;

import java.util.List;

@RestController
@RequestMapping(path = "song")
public class SongController {
    @Autowired
    SongService service;

    @PostMapping(path = "add")
    public void addSong(@RequestBody Song song) {
        service.save(song);
    }

    @GetMapping(path = "getAll")
    public List<Song> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "get-by-id/{id}")
    public Song getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping(path = "get-by-name/{name}")
    public Song getByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
