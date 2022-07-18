package ru.crbpavel.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.crbpavel.music.model.PlayList;
import ru.crbpavel.music.model.Song;
import ru.crbpavel.music.security.DecodeTokenUtil;
import ru.crbpavel.music.service.PlayListService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "playList")
public class PlayListController {
    @Autowired
    PlayListService service;
    @Autowired
    private DecodeTokenUtil decodeTokenUtil;

    @PostMapping(path = "add")
    public void addAlbum(@RequestBody PlayList playList) {
        service.save(playList);
    }

    @GetMapping(path = "getAll")    //user
    public List<PlayList> getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        return service.getAll(username);
    }

    @GetMapping(path = "get-by-id/{id}")    //user
    public PlayList getById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        return service.getById(id, username);
    }

    @GetMapping(path = "get-by-name/{name}")    //user
    public PlayList getByName(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        return service.findByName(name, username);
    }

    @DeleteMapping(path = "delete/{id}")    //user
    public void deleteById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        service.deleteById(id, username);
    }

    @PutMapping(path = "update/")
    public void updatePlayList(@RequestBody PlayList playList, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        service.updatePlayList(playList, username);
    }

    @PatchMapping(path = "add-song/{id}&&")
    public void addSong(@PathVariable Long id, @RequestBody Song song, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        service.addSongById(id, song, username);
    }
}
