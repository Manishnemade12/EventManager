package com.quanti_learn.user_service.service.serviceImpl;

import com.quanti_learn.user_service.service.KafkaPublisherService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.quantilearn.eventmodels.ProfileCreatedDto;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaPublisherServiceImpl implements KafkaPublisherService {

    private final KafkaTemplate<String,Object> template;

    public KafkaPublisherServiceImpl(
            KafkaTemplate<String,Object> template
    ){
        this.template=template;
    }

    @Override
    public void publishMessage(ProfileCreatedDto dto) {
        CompletableFuture<SendResult<String,Object>> future=template.send("user-notification",dto);
        future.whenComplete((result,ex)->{
            if(ex==null){
                System.out.println("Sent Message: ["+dto.toString()+"]");
            }
            else{
                System.out.println("Unable to sent message: ["+dto.toString()+"]");
            }
        });
    }
}
