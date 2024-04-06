package ru.collbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.collbox.model.Account;
import ru.collbox.model.Category;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUser_IdAndTitle (Long userId, String title);

    Account findByIdAndUser_Id(Long accId, Long userId);

    List<Account> findAllByUser_Id(Long userId);
}
