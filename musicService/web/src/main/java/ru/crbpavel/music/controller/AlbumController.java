package ru.crbpavel.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.crbpavel.music.model.Album;
import ru.crbpavel.music.service.AlbumService;

import java.util.List;

@RestController
@RequestMapping(path = "album")
public class AlbumController {
    @Autowired
    AlbumService service;

    @PostMapping(path = "add")
    public void addAlbum(@RequestBody Album album) {
        service.save(album);
    }

    @GetMapping(path = "getAll")
    public List<Album> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "get-by-id/{id}")
    public Album getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping(path = "get-by-name/{name}")
    public Album getByName(@PathVariable String name) {
        return service.findByTitle(name);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
