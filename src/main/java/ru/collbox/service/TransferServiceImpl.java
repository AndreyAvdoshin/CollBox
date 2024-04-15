package ru.collbox.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.collbox.dto.TransactionDto;
import ru.collbox.dto.TransactionFullDto;
import ru.collbox.dto.TransferDto;
import ru.collbox.dto.TransferFullDto;
import ru.collbox.model.Transfer;
import ru.collbox.model.mapper.TransferMapper;
import ru.collbox.repository.TransferRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TransferServiceImpl implements TransferService{

    private final TransferRepository repository;
    private final TransferMapper mapper;
    private final UserService userService;
    private final AccountService accountService;

    public TransferServiceImpl(TransferRepository repository, TransferMapper mapper, UserService userService, AccountService accountService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userService = userService;
        this.accountService = accountService;
    }

    @Transactional
    @Override
    public TransferFullDto createTransfer(TransferDto transferDto, Long userId){
        Transfer transfer = mapper.toTransfer(transferDto);

        transfer.setUser(userService.returnIfExists(userId));
        transfer.setSourceAccount(accountService.returnIfExists(userId, transferDto.getSourceAccountId()));
        transfer.setDestinationAccount(accountService.returnIfExists(userId, transferDto.getDestinationAccountId()));
        transfer = repository.save(transfer);

        //расчёт трансфера вычтаем из SourceAccount - amount и прибалвяем DestinationAccount + amount и заносим изминения в базу


        TransferFullDto transferFullDto = mapper.toTransferFullDto(transfer);
        log.info("Создание транзакции - {}", transferFullDto);
        return transferFullDto;
    }
}
