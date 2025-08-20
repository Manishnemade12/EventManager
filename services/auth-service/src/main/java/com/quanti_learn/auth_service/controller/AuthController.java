package com.quanti_learn.auth_service.controller;

import com.quanti_learn.auth_service.dto.AuthResponseDto;
import com.quanti_learn.auth_service.dto.LoginDto;
import com.quanti_learn.auth_service.dto.RegisterDto;
import com.quanti_learn.auth_service.dtoService.AccountDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AccountDtoService accountDtoService;

    public AuthController(AccountDtoService accountDtoService){
        this.accountDtoService=accountDtoService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> createAccount(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(accountDtoService.createAccount(registerDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginAccount(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(accountDtoService.loginAccount(loginDto),HttpStatus.ACCEPTED);
    }
}
