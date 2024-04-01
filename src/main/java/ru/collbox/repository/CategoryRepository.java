package ru.collbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.collbox.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
