package ru.collbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.collbox.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
