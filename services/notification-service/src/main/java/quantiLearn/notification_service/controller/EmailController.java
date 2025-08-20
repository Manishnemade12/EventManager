package quantiLearn.notification_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quantiLearn.notification_service.dto.EmailContentDto;
import quantiLearn.notification_service.dtoService.EmailDtoService;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {

    private final EmailDtoService emailDtoService;

    public EmailController(
            EmailDtoService emailDtoService
    ){
        this.emailDtoService=emailDtoService;
    }

    @PostMapping
    public ResponseEntity<?> sentEmail(@RequestBody EmailContentDto emailContentDto){
        this.emailDtoService.sentEmail(emailContentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
