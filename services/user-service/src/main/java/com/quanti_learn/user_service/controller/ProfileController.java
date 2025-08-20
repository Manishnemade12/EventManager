package com.quanti_learn.user_service.controller;

import com.quanti_learn.user_service.dto.ProfileDto;
import com.quanti_learn.user_service.dtoService.ProfileDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {
    private final ProfileDtoService service;

    public ProfileController(
            ProfileDtoService service
    ){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<Long> createProfile(@RequestBody ProfileDto profileDto){
        return new ResponseEntity<>(service.createProfile(profileDto).getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable("userId") String userId){
        return new ResponseEntity<>(service.getProfile(userId),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestBody ProfileDto profileDto){
        service.updateProfile(profileDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteProfile(@PathVariable("userId") String userId){
        service.deleteProfile(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
