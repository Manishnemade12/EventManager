package quantiLearn.progress_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quantiLearn.progress_service.dto.UserProgressionDto;
import quantiLearn.progress_service.dtoService.UserProgressionDtoService;

import java.util.List;

@RestController
@RequestMapping("api/v1/userprogression")
public class UserProgressionController {

    private final UserProgressionDtoService service;

    public UserProgressionController(
            UserProgressionDtoService service
    ){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<Long> createUserProgression(@RequestBody UserProgressionDto userProgressionDto){
        return new ResponseEntity<>(service.createUserProgression(userProgressionDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProgressionDto> getUserProgressionById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserProgressionDto>> getUserProgressionByUserId(@PathVariable("id") String id){
        return new ResponseEntity<>(service.getByUserId(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserProgressionDto> updateUserProgression(@RequestBody UserProgressionDto userProgressionDto){
        return new ResponseEntity<>(service.updateUserProgression(userProgressionDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserProgressionById(@PathVariable("id") Long id){
        service.deleteUserProgressionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserProgressionByUserId(@PathVariable("id") String id){
        service.deleteUserProgressionByUserId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
