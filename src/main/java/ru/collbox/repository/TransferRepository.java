package ru.collbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.collbox.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
