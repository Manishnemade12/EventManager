package com.quanti_learn.auth_service.controller;

import com.quanti_learn.auth_service.dto.AuthorityDto;
import com.quanti_learn.auth_service.dtoService.AuthorityDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/authority")
public class AuthorityController {

    private final AuthorityDtoService authorityDtoService;

    public AuthorityController(
            AuthorityDtoService authorityDtoService
    ){
        this.authorityDtoService=authorityDtoService;
    }

    @PostMapping
    public ResponseEntity<Long> createAuthority(@RequestBody AuthorityDto authorityDto){
        return new ResponseEntity<>(authorityDtoService.createAuthority(authorityDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorityDto>> getAllAuthority(){
        return new ResponseEntity<>(authorityDtoService.getAllAuthority(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateAuthority(@RequestBody AuthorityDto authorityDto){
        authorityDtoService.updateAuthority(authorityDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthority(@PathVariable("id") long id){
        authorityDtoService.deleteAuthority(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
