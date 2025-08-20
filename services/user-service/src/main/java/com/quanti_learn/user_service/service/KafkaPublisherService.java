package com.quanti_learn.user_service.service;


import com.quantilearn.eventmodels.ProfileCreatedDto;

public interface KafkaPublisherService {
    void publishMessage(ProfileCreatedDto dto);
}
