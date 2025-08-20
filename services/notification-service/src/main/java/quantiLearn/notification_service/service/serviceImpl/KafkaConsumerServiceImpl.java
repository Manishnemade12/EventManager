package quantiLearn.notification_service.service.serviceImpl;

import com.quantilearn.eventmodels.ProfileCreatedDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import quantiLearn.notification_service.service.EmailService;

@Service
public class KafkaConsumerServiceImpl{

    private final EmailService service;

    public KafkaConsumerServiceImpl(
            EmailService service
    ){
        this.service=service;
    }

    @KafkaListener(topics = "user-notification", groupId = "notification-group")
    public void profileCreatedConsumer(ProfileCreatedDto profileCreatedDto) {
        service.sentProfileCreatedEmail(profileCreatedDto);
        System.out.println(profileCreatedDto.toString());
    }
}
