package com.quantilearn.lesson_service.repository;

import com.quantilearn.lesson_service.entity.LessonContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonContentRepository  extends JpaRepository<LessonContent,Long> {
    List<LessonContent> findAllByLessonId(Long id);
}
