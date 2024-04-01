package ru.collbox.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.collbox.dto.TransactionDto;
import ru.collbox.model.Transaction;
import ru.collbox.model.mapper.TransactionMapper;
import ru.collbox.repository.AccountRepository;
import ru.collbox.repository.CategoryRepository;
import ru.collbox.repository.TransactionRepository;
import ru.collbox.repository.UserRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository repository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper mapper;


    @Transactional
    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = mapper.toTransaction(transactionDto);
        transaction.setUser(userRepository.findById(transactionDto.getUser()).get());
        transaction.setCategory(categoryRepository.findById(transactionDto.getCategory()).get());
        transaction.setAccount(accountRepository.findById(transactionDto.getAccount()).get());
        log.info("Создание транзакции - {}", transaction);
        transaction = repository.save(transaction);
        return mapper.toTransactionDto(transaction);
    }
}
