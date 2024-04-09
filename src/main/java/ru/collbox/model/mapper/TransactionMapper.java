package ru.collbox.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.collbox.dto.TransactionDto;
import ru.collbox.model.Transaction;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CategoryMapper.class, AccountMapper.class})
public interface TransactionMapper {

    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    //@Mapping(target = "user.id", source = "transactionDto.user")
    //@Mapping(target = "category.id", source = "transactionDto.category")
    //@Mapping(target = "account.id", source = "transactionDto.account")
    Transaction toTransaction(TransactionDto transactionDto);

    //@Mapping(target = "user", source = "transaction.user.id")
    //@Mapping(target = "category", source = "transaction.category.id")
    //@Mapping(target = "account", source = "transaction.account.id")
    TransactionDto toTransactionDto(Transaction transaction);
}
