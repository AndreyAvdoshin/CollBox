package ru.collbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.collbox.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByUser_IdAndTitle (Long userId, String title);

    List<Category> findAllByUser_Id(Long userId);

    Category findByIdAndUser_Id(Long catId, Long userId);
}
