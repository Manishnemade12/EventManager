package com.quanti_learn.auth_service.dtoService.dtoServiceImpl;

import com.quanti_learn.auth_service.dto.AccountDto;
import com.quanti_learn.auth_service.dto.AuthResponseDto;
import com.quanti_learn.auth_service.dto.LoginDto;
import com.quanti_learn.auth_service.dto.RegisterDto;
import com.quanti_learn.auth_service.dtoService.AccountDtoService;
import com.quanti_learn.auth_service.entity.Account;
import com.quanti_learn.auth_service.mapper.AccountMapper;
import com.quanti_learn.auth_service.security.JwtService;
import com.quanti_learn.auth_service.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDtoServiceImpl implements AccountDtoService {

    private final AccountMapper accountMapper;
    private final AccountService accountService;
    private final JwtService jwtService;

    public AccountDtoServiceImpl(
            AccountMapper accountMapper,
            AccountService accountService,
            JwtService jwtService
    ){
        this.accountMapper=accountMapper;
        this.accountService=accountService;
        this.jwtService=jwtService;
    }

    @Override
    public AuthResponseDto createAccount(RegisterDto registerDto) {
        Account account=accountService.createAccount(Account.builder().email(registerDto.getEmail()).password(registerDto.getPassword()).build());
        String token=jwtService.generateToken(account);
        return new AuthResponseDto(account.getUserId(),token);
    }

    @Override
    public AuthResponseDto loginAccount(LoginDto loginDto) {
        Account account=accountService.loginAccount(loginDto.getEmail(), loginDto.getPassword());
        String token=jwtService.generateToken(account);
        return new AuthResponseDto(account.getUserId(),token);
    }

    @Override
    public AccountDto getAccount(String userId) {
        return accountMapper.toDto(accountService.getAccount(userId));
    }

    @Override
    public List<AccountDto> getAllAccount() {
        return accountService.getAllAccount().stream().map(accountMapper::toDto).toList();
    }

    @Override
    public void updateAccountEmail(long id, String email) {
        accountService.updateAccountEmail(id,email);
    }

    @Override
    public void updateAccountPassword(long id, String password) {
        accountService.updateAccountPassword(id,password);
    }

    @Override
    public void deleteAccount(long id) {
        accountService.deleteAccount(id);
    }

    @Override
    public void addRoleToUser(String userId, String roleName) {
        accountService.addRoleToUser(userId,roleName);
    }
}
