package com.quantilearn.lesson_service.dtoService.dtoServiceImpl;

import com.quantilearn.lesson_service.dto.AILessonContentDto;
import com.quantilearn.lesson_service.dto.AILessonResponse;
import com.quantilearn.lesson_service.dto.LessonContentDto;
import com.quantilearn.lesson_service.dto.LessonDto;
import com.quantilearn.lesson_service.dtoService.LessonDtoService;
import com.quantilearn.lesson_service.mapper.LessonMapper;
import com.quantilearn.lesson_service.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonDtoServiceImpl implements LessonDtoService {

    private final LessonService service;
    private final LessonMapper mapper;

    public LessonDtoServiceImpl(
            LessonService service,
            LessonMapper mapper
    ){
        this.service=service;
        this.mapper=mapper;
    }

    @Override
    public LessonDto createLesson(LessonDto lesson) {
        lesson.setId(null);
        return mapper.toDto(service.createLesson(mapper.fromDto(lesson)));
    }

    @Override
    public LessonDto getLessonById(Long id) {
        return mapper.toDto(service.getLessonById(id));
    }

    @Override
    public List<LessonDto> getLessonsByAuthorId(String id) {
        return service.getLessonsByAuthorId(id).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public LessonDto updateLesson(LessonDto lesson) {
        return mapper.toDto(service.updateLesson(mapper.fromDto(lesson)));
    }

    @Override
    public void deleteLesson(Long id) {
        service.deleteLesson(id);
    }
}
