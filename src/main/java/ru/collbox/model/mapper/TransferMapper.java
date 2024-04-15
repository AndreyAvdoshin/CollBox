package ru.collbox.model.mapper;

import org.mapstruct.*;
import ru.collbox.dto.TransferDto;
import ru.collbox.dto.TransferFullDto;
import ru.collbox.model.Transfer;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = AccountMapper.class)
public interface TransferMapper {

    @Mapping(target = "transferDate", expression = "java(java.time.LocalDateTime.now())")
    Transfer toTransfer(TransferDto transferDto);

    TransferDto toTransferDto(Transfer transfer);

    @Mapping(source = "transfer.sourceAccount.id", target = "sourceAccount.id")
    @Mapping(source = "transfer.destinationAccount.id", target = "destinationAccount.id")
    TransferFullDto toTransferFullDto(Transfer transfer);
}
