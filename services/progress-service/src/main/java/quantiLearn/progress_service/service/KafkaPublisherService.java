package quantiLearn.progress_service.service;

import com.quantilearn.eventmodels.SkillsObtained;

public interface KafkaPublisherService {
    void publishMessage(SkillsObtained skillsObtained);
}
