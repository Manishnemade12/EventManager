package com.quantilearn.lesson_service.service;

import com.quantilearn.lesson_service.entity.LessonContent;

import java.util.List;

public interface LessonContentService {
    LessonContent createLessonContent(LessonContent lessonContent);
    LessonContent getLessonContentById(Long id);
    List<LessonContent> getLessonContentsByLessonId(Long id);
    LessonContent updateLessonContent(LessonContent lessonContent);
    void deleteLessonContent(Long id);
}
