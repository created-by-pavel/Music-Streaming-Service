package ru.crbpavel.music.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.crbpavel.music.model.Album;
import ru.crbpavel.music.model.SecurityModel;
import ru.crbpavel.music.model.Song;
import ru.crbpavel.music.model.User;
import ru.crbpavel.music.repository.UserRepository;
import ru.crbpavel.music.tool.MusicServiceException;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    SecurityModelService securityModelService;

    public User save(User user) {
        return repository.save(user);
    }

    public User getById(Long id) {
        return repository.getById(id);
    }

    public Set<Song> getAllSongs(String username) {
        return repository.findUserBySecurityModelId(getSecurityModelId(username)).getSongs();
    }

    public Set<Album> getAllAlbums(String username) {
        return repository.findUserBySecurityModelId(getSecurityModelId(username)).getAlbums();
    }

    public void delete(String username) {
        repository.delete(repository.findUserBySecurityModelId(getSecurityModelId(username)));
    }

    public void addSong(Song song, String username) {
        User existingUser = repository.findUserBySecurityModelId(getSecurityModelId(username));
        if (existingUser == null) throw new MusicServiceException("cant find user");
        existingUser.addSong(song);
        repository.save(existingUser);
    }

    public void addAlbum(Album album, String username) {
        User existingUser = repository.findUserBySecurityModelId(getSecurityModelId(username));
        if (existingUser == null) throw new MusicServiceException("cant find user");
        existingUser.addAlbum(album);
        repository.save(existingUser);
    }

    public void updateUser(User user, String username) {
        User existingUser = repository.findById(user.getId()).orElse(null);
        if (existingUser == null) throw new MusicServiceException("cant find user");
        if (existingUser.getId() != repository.findUserBySecurityModelId(getSecurityModelId(username)).getId()) {
            throw new MusicServiceException("access denied");
        }
        existingUser.setSecurityModel(user.getSecurityModel());
        existingUser.setSongs(user.getSongs());
        repository.save(existingUser);
    }

    public void deleteSong(Song song, String username) {
        User existingUser = repository.findUserBySecurityModelId(getSecurityModelId(username));
        if (existingUser == null) throw new MusicServiceException("cant find user");
        existingUser.deleteSong(song);
        repository.save(existingUser);
    }

    public String findUserBySecurityModel(String username) {
        return repository.findUserBySecurityModelId(getSecurityModelId(username)).getUsername();
    }

    private Long getSecurityModelId(String username) {
        SecurityModel securityModel = securityModelService.findByName(username);
        if (securityModel == null) throw new MusicServiceException("cant find securityModel");
        return securityModel.getId();
    }
}
