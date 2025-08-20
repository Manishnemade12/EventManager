package com.quanti_learn.auth_service.controller;

import com.quanti_learn.auth_service.dto.AccountDto;
import com.quanti_learn.auth_service.dtoService.AccountDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountDtoService accountDtoService;

    public AccountController(
            AccountDtoService accountDtoService
    ){
        this.accountDtoService=accountDtoService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("userId") String userId){
        return new ResponseEntity<>(accountDtoService.getAccount(userId), HttpStatus.OK);
    }

    @PostMapping("/newRole")
    public ResponseEntity addNewRole(@RequestParam String userId, @RequestParam String roleName){
        accountDtoService.addRoleToUser(userId,roleName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccount(){
        return new ResponseEntity<>(accountDtoService.getAllAccount(),HttpStatus.OK);
    }

    @PutMapping("/email")
    public ResponseEntity updateEmail(@RequestParam() long id, @RequestParam String email){
        accountDtoService.updateAccountEmail(id,email);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/password")
    public ResponseEntity updatePassword(@RequestParam() long id, @RequestParam String password){
        accountDtoService.updateAccountPassword(id,password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccount(@PathVariable("id") long id){
        accountDtoService.deleteAccount(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
