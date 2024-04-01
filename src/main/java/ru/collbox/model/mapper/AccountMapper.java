package ru.collbox.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.collbox.dto.AccountDto;
import ru.collbox.model.Account;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    @Mapping(target = "user.id", source = "userId")
    Account toAccount(AccountDto accountDto);

    @Mapping(target = "userId", source = "account.user.id")
    AccountDto toAccountDto(Account account);
}
