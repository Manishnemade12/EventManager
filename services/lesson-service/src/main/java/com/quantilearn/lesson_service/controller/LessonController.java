package com.quantilearn.lesson_service.controller;

import com.quantilearn.lesson_service.dto.AILessonResponse;
import com.quantilearn.lesson_service.dto.LessonDto;
import com.quantilearn.lesson_service.dtoService.LessonDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lesson")
public class LessonController {

    private final LessonDtoService service;

    public LessonController(
            LessonDtoService service
    ){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonDto lesson){
        return new ResponseEntity<>(service.createLesson(lesson), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.getLessonById(id),HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<LessonDto>> getAllLessonsByAuthorId(@PathVariable("id") String id){
        return new ResponseEntity<>(service.getLessonsByAuthorId(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LessonDto> updateLesson(@RequestBody LessonDto lessonDto){
        return new ResponseEntity<>(service.updateLesson(lessonDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable("id") Long id){
        service.deleteLesson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
