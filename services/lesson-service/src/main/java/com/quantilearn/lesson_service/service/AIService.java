package com.quantilearn.lesson_service.service;

import com.quantilearn.lesson_service.dto.AILessonContentDto;
import com.quantilearn.lesson_service.dto.AILessonContentRequestDto;
import com.quantilearn.lesson_service.dto.AILessonResponse;
import com.quantilearn.lesson_service.dto.LessonDto;

import java.util.List;

public interface AIService {
    AILessonResponse getLessonContent(AILessonContentRequestDto lessonContentRequestDto);
}
