package com.quantilearn.lesson_service.controller;

import com.quantilearn.lesson_service.dto.AILessonContentRequestDto;
import com.quantilearn.lesson_service.dto.AILessonResponse;
import com.quantilearn.lesson_service.dto.LessonContentDto;
import com.quantilearn.lesson_service.dtoService.LessonContentDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lessonContent")
public class LessonContentController {

    private final LessonContentDtoService service;

    public LessonContentController(
            LessonContentDtoService service
    ){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<LessonContentDto> createLessonContent(@RequestBody LessonContentDto lessonContentDto){
        return new ResponseEntity<>(service.createLessonContent(lessonContentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonContentDto> getLessonContentById(@PathVariable Long id){
        return new ResponseEntity<>(service.getLessonContentById(id),HttpStatus.OK);
    }

    @GetMapping("/lesson/{id}")
    public ResponseEntity<List<LessonContentDto>> getLessonContentByLessonId(@PathVariable Long id){
        return new ResponseEntity<>(service.getLessonContentsByLessonId(id),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LessonContentDto> updateLessonContent(@RequestBody LessonContentDto lessonContentDto){
        return new ResponseEntity<>(service.updateLessonContent(lessonContentDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLessonContent(@PathVariable Long id){
        service.deleteLessonContent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/ai/lesson")
    public ResponseEntity<AILessonResponse> getAILessonResponse(@RequestBody AILessonContentRequestDto lessonContentRequestDto){
        return new ResponseEntity<>(service.generateLessonContentWithAI(lessonContentRequestDto),HttpStatus.OK);
    }

    @PostMapping("/ai/lesson/{id}/save")
    public ResponseEntity<?> saveAILesson(@PathVariable("id") Long id,@RequestBody AILessonResponse aiLessonResponse){
        service.saveAILessonContent(id,aiLessonResponse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
