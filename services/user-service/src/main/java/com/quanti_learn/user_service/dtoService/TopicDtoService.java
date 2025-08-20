package com.quanti_learn.user_service.dtoService;

import com.quanti_learn.user_service.dto.TopicDto;

import java.util.List;

public interface TopicDtoService {
    TopicDto createTopic(TopicDto topicDto);
    TopicDto getTopic(Long id);
    List<TopicDto> getAllTopic();
    TopicDto updateTopic(TopicDto topicDto);
    void deleteTopic(Long id);
}
