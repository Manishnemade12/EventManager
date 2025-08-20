package quantiLearn.notification_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quantiLearn.notification_service.dto.NotificationLogDto;
import quantiLearn.notification_service.dtoService.NotificationLogsDtoService;

@RestController
@RequestMapping("api/v1/notificationLog")
public class NotificationLogsController {

    private final NotificationLogsDtoService service;

    public NotificationLogsController(
            NotificationLogsDtoService service
    ){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<NotificationLogDto> createLog(@RequestBody NotificationLogDto notificationLogDto){
        return new ResponseEntity<>(service.createLog(notificationLogDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> markAsRead(@PathVariable Long id){
        service.markAsRead(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLog(@PathVariable Long id){
        service.deleteLog(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
