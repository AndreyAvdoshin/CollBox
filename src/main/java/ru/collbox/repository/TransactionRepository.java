package ru.collbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.collbox.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
