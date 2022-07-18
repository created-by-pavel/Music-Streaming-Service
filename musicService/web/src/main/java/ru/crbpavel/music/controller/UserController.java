package ru.crbpavel.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.crbpavel.music.model.Album;
import ru.crbpavel.music.model.Song;
import ru.crbpavel.music.model.User;
import ru.crbpavel.music.security.DecodeTokenUtil;
import ru.crbpavel.music.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping(path = "user")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    private DecodeTokenUtil decodeTokenUtil;

    @PostMapping(path = "add")
    public void addUser(@RequestBody User user) {
        service.save(user);
    }

    @GetMapping(path = "get-by-id/{id}")
    public User getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping(path = "get-all-songs")
    public Set<Song> getAllSongs(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        return service.getAllSongs(username);
    }

    @GetMapping(path = "get-all-albums")
    public Set<Album> getAllAlbums(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        return service.getAllAlbums(username);
    }

    @GetMapping(path = "find-user-by-security-model")
    public String findUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        return service.findUserBySecurityModel(username);
    }


    @DeleteMapping(path = "delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        service.delete(username);
    }

    @PutMapping(path = "update")
    public void updateUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        service.updateUser(user, username);
    }

    @PatchMapping(path = "add-song")
    public void addSong(@RequestBody Song song, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        service.addSong(song, username);
    }

    @DeleteMapping(path = "delete-song")
    public void deleteSong(@RequestBody Song song, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
    }
}
