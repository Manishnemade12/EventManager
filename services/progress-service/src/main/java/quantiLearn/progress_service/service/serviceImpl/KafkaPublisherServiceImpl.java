package quantiLearn.progress_service.service.serviceImpl;

import com.quantilearn.eventmodels.SkillsObtained;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import quantiLearn.progress_service.service.KafkaPublisherService;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaPublisherServiceImpl implements KafkaPublisherService {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public KafkaPublisherServiceImpl(
            KafkaTemplate<String,Object> kafkaTemplate
    ){
        this.kafkaTemplate=kafkaTemplate;
    }

    @Override
    public void publishMessage(SkillsObtained skillsObtained) {
        CompletableFuture<SendResult<String,Object>> future=kafkaTemplate.send("progress-update",skillsObtained);
        System.out.println("Message sent!");
        future.whenComplete((result,exc)->{
            if(exc==null){
                System.out.println("Sent Message: ["+skillsObtained.toString()+"]");
            }
            else{
                System.out.println("Unable to sent message: ["+skillsObtained.toString()+"]");
            }
        });
    }
}
