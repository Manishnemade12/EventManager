package com.quanti_learn.user_service.service.serviceImpl;

import com.quanti_learn.user_service.entity.Profile;
import com.quanti_learn.user_service.entity.Topic;
import com.quantilearn.eventmodels.SkillsObtained;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class KafkaConsumerServiceImpl {

    private final ProfileServiceImpl service;

    public KafkaConsumerServiceImpl(
            ProfileServiceImpl service
    ){
        this.service=service;
    }

    @KafkaListener(topics = "progress-update",groupId = "progress-group")
    public void updateUserLevel(SkillsObtained skillsObtained){
       Profile profile= service.getProfile(skillsObtained.getUserId());
       Set<Topic> learningGoal=profile.getLearningGoal();
       skillsObtained.getNewSkills().forEach((skill)->{
           learningGoal.forEach((goal)->{
               if(goal.getName().equals(skill)){
                   learningGoal.remove(goal);
               }
           });
       });
       service.updateProfile(profile);
    }
}
