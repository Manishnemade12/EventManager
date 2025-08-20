package com.quanti_learn.user_service.mapper;

import com.quanti_learn.user_service.dto.TopicDto;
import com.quanti_learn.user_service.entity.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    Topic fromDto(TopicDto topicDto);
    TopicDto toDto(Topic topic);
}
