package com.quantilearn.lesson_service.service;

import com.quantilearn.lesson_service.entity.Lesson;

import java.util.List;

public interface LessonService {
    Lesson createLesson(Lesson lesson);
    Lesson getLessonById(Long id);
    List<Lesson> getLessonsByAuthorId(String id);
    Lesson updateLesson(Lesson lesson);
    void deleteLesson(Long id);
}
