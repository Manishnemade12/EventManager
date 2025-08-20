package com.quanti_learn.user_service.service;

import com.quanti_learn.user_service.entity.Topic;

import java.util.List;

public interface TopicService {
    Topic createTopic(Topic topic);
    Topic getTopic(Long id);
    List<Topic> getAllTopic();
    Topic updateTopic(Topic topic);
    void deleteTopic(Long id);
}
