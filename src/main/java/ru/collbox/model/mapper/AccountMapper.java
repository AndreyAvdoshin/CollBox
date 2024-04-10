package ru.collbox.model.mapper;

import org.mapstruct.*;
import ru.collbox.dto.AccountDto;
import ru.collbox.model.Account;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    Account toAccount(AccountDto accountDto);

    AccountDto toAccountDto(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account updateAccount(@MappingTarget Account account, AccountDto accountDto);
}
