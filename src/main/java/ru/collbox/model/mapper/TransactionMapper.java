package ru.collbox.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import ru.collbox.dto.TransactionDto;
import ru.collbox.model.Transaction;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "user.id", source = "transactionDto.user")
    @Mapping(target = "category.id", source = "transactionDto.category")
    @Mapping(target = "account.id", source = "transactionDto.account")
    Transaction toTransaction(TransactionDto transactionDto);


    TransactionDto toTransactionDto(Transaction transaction);
}
