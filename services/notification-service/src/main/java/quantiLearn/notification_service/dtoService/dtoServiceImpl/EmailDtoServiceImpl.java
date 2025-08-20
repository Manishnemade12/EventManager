package quantiLearn.notification_service.dtoService.dtoServiceImpl;

import org.springframework.stereotype.Service;
import quantiLearn.notification_service.dto.EmailContentDto;
import quantiLearn.notification_service.dtoService.EmailDtoService;
import quantiLearn.notification_service.service.EmailService;

@Service
public class EmailDtoServiceImpl implements EmailDtoService {

    private final EmailService emailService;

    public EmailDtoServiceImpl(
            EmailService emailService
    ){
        this.emailService=emailService;
    }

    @Override
    public void sentEmail(EmailContentDto emailContentDto) {
        this.emailService.sentEmail(emailContentDto);
    }
}
