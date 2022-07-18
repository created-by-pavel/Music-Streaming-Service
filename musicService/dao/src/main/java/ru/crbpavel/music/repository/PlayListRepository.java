package ru.crbpavel.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.crbpavel.music.model.PlayList;

import java.util.List;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Long> {
    PlayList findByName(String name);

    List<PlayList> findByUserId(Long id);

    PlayList findByUserIdAndId(Long userId, Long id);

    PlayList findByUserIdAndName(Long userId, String name);
}
