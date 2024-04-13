package ru.collbox.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.collbox.dto.TransactionDto;
import ru.collbox.dto.TransactionFullDto;
import ru.collbox.model.Transaction;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CategoryMapper.class, AccountMapper.class})
public interface TransactionMapper {

    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    //@Mapping(target = "user.id", source = "transactionDto.user")
    //@Mapping(target = "category.id", source = "transactionDto.category")
    //@Mapping(source = "transactionDto.accountId", target = "account.id")
    Transaction toTransaction(TransactionDto transactionDto);

    //@Mapping(target = "user", source = "transaction.user.id")
    @Mapping(source = "transaction.category.id", target = "categoryId")
    @Mapping(source = "transaction.account.id", target = "accountId")
    TransactionDto toTransactionDto(Transaction transaction);

    @Mapping(source = "transaction.category.id", target = "category.id")
    @Mapping(source = "transaction.account.id", target = "account.id")
    TransactionFullDto toTransactionFullResponseDto(Transaction transaction);
}
