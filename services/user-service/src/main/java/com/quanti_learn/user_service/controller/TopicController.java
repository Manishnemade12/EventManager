package com.quanti_learn.user_service.controller;


import com.quanti_learn.user_service.dto.TopicDto;
import com.quanti_learn.user_service.dtoService.TopicDtoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/topic")
public class TopicController {
    private final TopicDtoService service;

    public TopicController(
            TopicDtoService service
    )
    {
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<Long> createTopic(@RequestBody TopicDto topicDto){
        return new ResponseEntity<>(service.createTopic(topicDto).getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getTopic(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getTopic(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TopicDto>> getAllTopic(){
        return new ResponseEntity<>(service.getAllTopic(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TopicDto> updateTopic(@RequestBody TopicDto topicDto){
        return new ResponseEntity<>(service.updateTopic(topicDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopic(@PathVariable("id") Long id){
        service.deleteTopic(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
