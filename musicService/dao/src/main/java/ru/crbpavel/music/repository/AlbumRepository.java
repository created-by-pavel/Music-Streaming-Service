package ru.crbpavel.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.crbpavel.music.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findByTitle(String title);
}
