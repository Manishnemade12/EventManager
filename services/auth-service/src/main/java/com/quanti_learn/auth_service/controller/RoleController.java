package com.quanti_learn.auth_service.controller;

import com.quanti_learn.auth_service.dto.RoleDto;
import com.quanti_learn.auth_service.dtoService.RoleDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {

    private final RoleDtoService roleDtoService;

    public RoleController(RoleDtoService roleDtoService){
        this.roleDtoService=roleDtoService;
    }

    @PostMapping
    public ResponseEntity<Long> createRole(@RequestBody RoleDto roleDto){
        return new ResponseEntity<>(roleDtoService.createRole(roleDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleDto>> getAllRole(){
        return new ResponseEntity<>(roleDtoService.getAllRole(),HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoleDto> getRoleByName(@PathVariable String name){
        return new ResponseEntity<>(roleDtoService.getRoleByName(name),HttpStatus.OK);
    }

    @GetMapping("/addAuthorities")
    public ResponseEntity addAuthoritiesToRole(@RequestParam String role, @RequestParam Set<String> authorities){
        roleDtoService.addAuthorityToRole(role,authorities);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateRole(@RequestBody RoleDto roleDto){
        roleDtoService.updateRole(roleDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable("id") long id){
        roleDtoService.deleteRole(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
