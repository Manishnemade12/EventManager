package quantiLearn.notification_service.dtoService;

import quantiLearn.notification_service.dto.EmailContentDto;

public interface EmailDtoService {
    void sentEmail(EmailContentDto emailContentDto);
}
