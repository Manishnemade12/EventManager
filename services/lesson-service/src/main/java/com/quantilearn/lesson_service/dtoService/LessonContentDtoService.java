package com.quantilearn.lesson_service.dtoService;


import com.quantilearn.lesson_service.dto.AILessonContentRequestDto;
import com.quantilearn.lesson_service.dto.AILessonResponse;
import com.quantilearn.lesson_service.dto.LessonContentDto;

import java.util.List;

public interface LessonContentDtoService {
    LessonContentDto createLessonContent(LessonContentDto lessonContent);
    LessonContentDto getLessonContentById(Long id);
    List<LessonContentDto> getLessonContentsByLessonId(Long id);
    LessonContentDto updateLessonContent(LessonContentDto lessonContent);
    void deleteLessonContent(Long id);
    AILessonResponse generateLessonContentWithAI(AILessonContentRequestDto lessonContentRequestDto);
    void saveAILessonContent(Long id, AILessonResponse aiLessonResponse);
}
