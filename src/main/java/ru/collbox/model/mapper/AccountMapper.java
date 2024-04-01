package ru.collbox.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.collbox.dto.AccountDto;
import ru.collbox.model.Account;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    //@Mapping(target = "user.id", source = "accountDto.userId")
    Account toAccount(AccountDto accountDto);

    //@Mapping(target = "userId", source = "account.user.id")
    AccountDto toAccountDto(Account account);
}
