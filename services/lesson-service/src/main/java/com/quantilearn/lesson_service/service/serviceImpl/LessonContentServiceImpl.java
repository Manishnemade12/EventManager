package com.quantilearn.lesson_service.service.serviceImpl;

import com.quantilearn.lesson_service.entity.Lesson;
import com.quantilearn.lesson_service.entity.LessonContent;
import com.quantilearn.lesson_service.exception.exceptions.CustomException;
import com.quantilearn.lesson_service.exception.exceptions.CustomNotFoundException;
import com.quantilearn.lesson_service.repository.LessonContentRepository;
import com.quantilearn.lesson_service.repository.LessonRepository;
import com.quantilearn.lesson_service.service.LessonContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LessonContentServiceImpl implements LessonContentService {

    private final LessonContentRepository repository;
    private final LessonRepository lessonRepository;

    public LessonContentServiceImpl(
            LessonContentRepository repository,
            LessonRepository lessonRepository
    ){
        this.repository=repository;
        this.lessonRepository=lessonRepository;
    }

    @Override
    @Transactional
    public LessonContent createLessonContent(LessonContent lessonContent) {
        Lesson lesson=lessonContent.getLesson();
        Optional<Lesson> lessonOptional=lessonRepository.findById(lesson.getId());
        if(lessonOptional.isPresent()){
            lessonContent.setLesson(lessonOptional.get());
            return repository.save(lessonContent);
        }
        throw new CustomException("Lesson Content Failed!");
    }

    @Override
    public LessonContent getLessonContentById(Long id) {
        Optional<LessonContent> lessonContentOptional=repository.findById(id);
        if(lessonContentOptional.isPresent()){
            return lessonContentOptional.get();
        }
        throw new CustomNotFoundException("Lesson Content Not found!");
    }

    @Override
    public List<LessonContent> getLessonContentsByLessonId(Long id) {
        return repository.findAllByLessonId(id);
    }

    @Override
    public LessonContent updateLessonContent(LessonContent lessonContent) {
        Optional<LessonContent> lessonContentOptional=repository.findById(lessonContent.getId());
        if(lessonContentOptional.isPresent()){
            return repository.save(lessonContent);
        }
        throw new CustomException("Lesson Update Failed!");
    }

    @Override
    public void deleteLessonContent(Long id) {
        Optional<LessonContent> lessonContentOptional=repository.findById(id);
        if(lessonContentOptional.isPresent()){
            repository.delete(lessonContentOptional.get());
        }
        else{
            throw new CustomException("Lesson Content Delete failed!");
        }
    }
}
