package ru.collbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.collbox.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByUserIdAndTitle (Long userId, String title);

    List<Category> findAllByUserId(Long userId);

    Optional<Category> findByIdAndUserId(Long catId, Long userId);
}
