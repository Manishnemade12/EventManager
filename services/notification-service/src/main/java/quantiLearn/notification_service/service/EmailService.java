package quantiLearn.notification_service.service;

import com.quantilearn.eventmodels.ProfileCreatedDto;
import quantiLearn.notification_service.dto.EmailContentDto;

public interface EmailService {
    void sentEmail(EmailContentDto emailContentDto);
    void sentProfileCreatedEmail(ProfileCreatedDto profileCreatedDto);
}
