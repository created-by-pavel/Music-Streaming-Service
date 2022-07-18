package ru.crbpavel.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.crbpavel.music.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserBySecurityModelId(Long id);
}
