package com.quantilearn.lesson_service.dtoService;

import com.quantilearn.lesson_service.dto.AILessonResponse;
import com.quantilearn.lesson_service.dto.LessonDto;

import java.util.List;

public interface LessonDtoService {
    LessonDto createLesson(LessonDto lesson);
    LessonDto getLessonById(Long id);
    List<LessonDto> getLessonsByAuthorId(String id);
    LessonDto updateLesson(LessonDto lesson);
    void deleteLesson(Long id);
}
