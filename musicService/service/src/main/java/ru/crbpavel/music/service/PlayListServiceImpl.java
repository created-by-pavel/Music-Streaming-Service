package ru.crbpavel.music.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.crbpavel.music.model.PlayList;
import ru.crbpavel.music.model.SecurityModel;
import ru.crbpavel.music.model.Song;
import ru.crbpavel.music.model.User;
import ru.crbpavel.music.repository.PlayListRepository;
import ru.crbpavel.music.repository.UserRepository;
import ru.crbpavel.music.tool.MusicServiceException;

import java.util.List;

@Service
public class PlayListServiceImpl implements PlayListService {
    @Autowired
    PlayListRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityModelService securityModelService;


    public void save(PlayList playList) {
        repository.save(playList);
    }

    public List<PlayList> getAll(String username) {
        return repository.findByUserId(getUserId(username));
    }

    public PlayList getById(Long id, String username) {
        return repository.findByUserIdAndId(getUserId(username), id);
    }

    public PlayList findByName(String name, String username) {
        return repository.findByUserIdAndName(getUserId(username), name);
    }

    public void deleteById(Long id, String username) {
        PlayList playList = repository.findByUserIdAndId(getUserId(username), id);
        repository.delete(playList);
    }

    public void updatePlayList(PlayList playList, String username) {
        PlayList existingPlayList = repository.findByUserIdAndId(getUserId(username), playList.getId());
        if (existingPlayList == null) throw new MusicServiceException("cant find user");
        existingPlayList.setName(playList.getName());
        existingPlayList.setSongs(playList.getSongs());
        repository.save(existingPlayList);
    }

    public void addSongById(Long id, Song song, String username) {
        PlayList existingPlayList = repository.findByUserIdAndId(getUserId(username), id);
        if (existingPlayList == null) throw new MusicServiceException("cant find user");
        existingPlayList.getSongs().add(song); // set
        repository.save(existingPlayList);
    }

    private Long getUserId(String username) {
        SecurityModel securityModel = securityModelService.findByName(username);
        if (securityModel == null) throw new MusicServiceException("cant find securityModel");
        User user = userRepository.findUserBySecurityModelId(securityModel.getId());
        if (user == null) throw new MusicServiceException("cant find user");
        return user.getId();
    }
}
