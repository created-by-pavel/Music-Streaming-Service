package ru.crbpavel.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.crbpavel.music.model.Artist;
import ru.crbpavel.music.service.ArtistService;


@RestController
@RequestMapping(path = "artist")
public class ArtistController {
    @Autowired
    ArtistService service;

    @PostMapping(path = "add")
    public void addAlbum(@RequestBody Artist artist) {
        service.save(artist);
    }

    @GetMapping(path = "get-by-id/{id}")
    public Artist getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping(path = "get-by-name/{name}")
    public Artist getByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
