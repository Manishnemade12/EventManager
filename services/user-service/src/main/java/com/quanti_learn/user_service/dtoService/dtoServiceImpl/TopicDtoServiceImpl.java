package com.quanti_learn.user_service.dtoService.dtoServiceImpl;

import com.quanti_learn.user_service.dto.TopicDto;
import com.quanti_learn.user_service.mapper.TopicMapper;
import com.quanti_learn.user_service.service.TopicService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicDtoServiceImpl implements com.quanti_learn.user_service.dtoService.TopicDtoService {
    private final TopicMapper mapper;
    private final TopicService service;

    public TopicDtoServiceImpl(
        TopicMapper mapper,
        TopicService service
    ){
        this.mapper=mapper;
        this.service=service;
    }


    @Override
    @CacheEvict(value = "allTopics", allEntries = true)
    public TopicDto createTopic(TopicDto topicDto) {
        return mapper.toDto(service.createTopic(mapper.fromDto(topicDto)));
    }

    @Override
    @Cacheable(key="#id", value = "Topic")
    public TopicDto getTopic(Long id) {
        return mapper.toDto(service.getTopic(id));
    }

    @Override
    @Cacheable(value = "allTopics")
    public List<TopicDto> getAllTopic() {
        return service.getAllTopic().stream().map(mapper::toDto).toList();
    }

    @Override
    @CachePut(key="#topicDto.id",value = "Topic")
    @CacheEvict(value = "allTopics", allEntries = true)
    public TopicDto updateTopic(TopicDto topicDto) {
        return mapper.toDto(service.updateTopic(mapper.fromDto(topicDto)));
    }

    @Override
    @CacheEvict(value = {"topics", "allTopics"}, key = "#id", allEntries = true)
    public void deleteTopic(Long id) {
        service.deleteTopic(id);
    }
}
