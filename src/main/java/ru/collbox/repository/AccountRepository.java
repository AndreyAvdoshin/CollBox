package ru.collbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.collbox.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
