package ru.collbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.collbox.model.Account;
import ru.collbox.model.Category;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUser_IdAndTitle (Long userId, String title);

    Account findAccountByUserIdAndTitle (Long userId, String title);

    Account findByIdAndUserId(Long accId, Long userId);

    List<Account> findAllByUserId(Long userId);
}
