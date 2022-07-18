package ru.crbpavel.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.crbpavel.music.model.SecurityModel;

@Repository
public interface SecurityModelRepository extends JpaRepository<SecurityModel, Long> {
    SecurityModel findByName(String name);
}
