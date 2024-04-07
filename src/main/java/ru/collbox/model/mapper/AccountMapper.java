package ru.collbox.model.mapper;

import org.mapstruct.*;
import ru.collbox.dto.AccountDto;
import ru.collbox.model.Account;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class})
public interface AccountMapper {

    //@Mapping(target = "user.id", source = "userId")
    Account toAccount(AccountDto accountDto);

    //@Mapping(target = "userId", source = "account.user.id")
    AccountDto toAccountDto(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account updateAccount(@MappingTarget Account account, AccountDto accountDto);
}
