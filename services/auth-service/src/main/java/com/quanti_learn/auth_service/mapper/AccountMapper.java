package com.quanti_learn.auth_service.mapper;

import com.quanti_learn.auth_service.dto.AccountDto;
import com.quanti_learn.auth_service.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account fromDto(AccountDto accountDto);
    AccountDto toDto(Account account);
}
