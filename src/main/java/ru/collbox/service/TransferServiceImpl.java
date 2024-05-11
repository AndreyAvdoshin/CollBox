package ru.collbox.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.collbox.dto.*;
import ru.collbox.exception.NotFoundException;
import ru.collbox.exception.NotOwnerException;
import ru.collbox.model.Account;
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

        //TODO расчёт трансфера вычтаем из SourceAccount - amount и прибалвяем DestinationAccount + amount и заносим изминения в базу
        //нужно учитывать наличие первой транзакции (доход) с этим Account (Как я тебя понял)
        //я предлагаю не учитывать транзакцию, т.к. у нас Account создается отдельно и баланс указывается при создании сяёта, без помощи транзакции
        //и мы можем сразу делать трансфер между считами , что ты на это скажешь ? (уже реализовал в создании и обнолвнеии)
        //либо проверять что балан не равен нулю и не было ни одной транзакции с этим счётом или баланс больше нуля , но не было транзакций

        transferBetweenAccounts(transfer, userId);

        TransferFullDto transferFullDto = mapper.toTransferFullDto(transfer);
        log.info("Создание транзакции - {}", transferFullDto);
        return transferFullDto;
    }

    @Transactional
    @Override
    public TransferFullDto updateTransferByUserId(UpdateTransferDto updateTransferDto, Long userId, Long transfId){
        Transfer transfer = getTransferBelongUser(userId, transfId);
        // отменяем предыдущее действие
        transferBetweenAccountsToDelete(transfer, userId);

        transfer = mapper.updateTransfer(transfer, updateTransferDto);
        updateTransfer(updateTransferDto, transfer, userId);
        transfer = repository.save(transfer);

        transferBetweenAccounts(transfer, userId);

        log.info("Обновление трансфера - {}", transfer);
        return mapper.toTransferFullDto(transfer);
    }

    @Transactional
    @Override
    public void deleteTransfer(Long userId, Long transfId) {
        Transfer transfer = getTransferBelongUser(userId, transfId);
        transferBetweenAccountsToDelete(transfer, userId);

        log.info("Удаление трансфера - {}", transfer);
        repository.delete(transfer);
    }

    private Transfer getTransferBelongUser(Long userId, Long transfId) {
        Transfer transfer = returnIfExists(transfId);
        userService.checkExistingUser(userId);
        if (!transfer.getUser().getId().equals(userId)) {
            throw new NotOwnerException("Transfer по id - " + transfId +
                    " не принадлежит пользователю по id - " + userId);
        }
        return transfer;
    }

    private Transfer returnIfExists(Long transfId) {
        return repository.findById(transfId)
                .orElseThrow(() -> new NotFoundException("Transfer", transfId));
    }

    private void updateTransfer(UpdateTransferDto updateTransferDto, Transfer transfer, Long userId) {
        transfer.setSourceAccount(updateTransferDto.getSourceAccountId() != null ?
                accountService.returnIfExists(userId, updateTransferDto.getSourceAccountId()) :
                transfer.getSourceAccount());

        transfer.setDestinationAccount(updateTransferDto.getDestinationAccountId() != null ?
                accountService.returnIfExists(userId, updateTransferDto.getDestinationAccountId()) :
                transfer.getDestinationAccount());
    }

    private void transferBetweenAccounts(Transfer transfer, Long userId){
        Account sourceAccount = accountService.returnIfExists(userId, transfer.getSourceAccount().getId());

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transfer.getAmount()));
        accountService.updateAccount(sourceAccount);

        Account destinationAccount = accountService.returnIfExists(userId, transfer.getDestinationAccount().getId());

        destinationAccount.setBalance(destinationAccount.getBalance().add(transfer.getAmount()));
        accountService.updateAccount(destinationAccount);
    }

    private void transferBetweenAccountsToDelete(Transfer transfer, Long userId) {
        Account sourceAccount = accountService.returnIfExists(userId, transfer.getSourceAccount().getId());

        sourceAccount.setBalance(sourceAccount.getBalance().add(transfer.getAmount()));
        accountService.updateAccount(sourceAccount);

        Account destinationAccount = accountService.returnIfExists(userId, transfer.getDestinationAccount().getId());

        destinationAccount.setBalance(destinationAccount.getBalance().subtract(transfer.getAmount()));
        accountService.updateAccount(destinationAccount);
    }
}
