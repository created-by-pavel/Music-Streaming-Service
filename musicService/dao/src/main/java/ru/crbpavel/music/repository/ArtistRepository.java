package ru.crbpavel.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.crbpavel.music.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByName(String name);
}
