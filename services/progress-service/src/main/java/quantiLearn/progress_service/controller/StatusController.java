package quantiLearn.progress_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quantiLearn.progress_service.dto.StatusDto;
import quantiLearn.progress_service.dtoService.StatusDtoService;

import java.util.List;

@RestController
@RequestMapping("api/v1/status")
public class StatusController {
    private final StatusDtoService service;

    public StatusController(
            StatusDtoService service
    ){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<Long> createStatusDto(@RequestBody StatusDto statusDto){
        return new ResponseEntity<>(this.service.createStatus(statusDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDto> getStatusById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getStatusById(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StatusDto>> getAllStatus(){
        return new ResponseEntity<>(this.service.getAllStatus(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StatusDto> updateStatus(@RequestBody StatusDto statusDto){
        return new ResponseEntity<>(this.service.updateStatus(statusDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStatus(@PathVariable Long id){
        service.deleteStatus(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
