package ru.crbpavel.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.crbpavel.music.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findByName(String name);
}
