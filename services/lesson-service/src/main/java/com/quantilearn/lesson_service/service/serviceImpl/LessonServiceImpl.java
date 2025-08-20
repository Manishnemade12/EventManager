package com.quantilearn.lesson_service.service.serviceImpl;

import com.quantilearn.lesson_service.entity.Lesson;
import com.quantilearn.lesson_service.entity.Skills;
import com.quantilearn.lesson_service.exception.exceptions.CustomException;
import com.quantilearn.lesson_service.exception.exceptions.CustomNotFoundException;
import com.quantilearn.lesson_service.repository.LessonRepository;
import com.quantilearn.lesson_service.repository.SkillsRepository;
import com.quantilearn.lesson_service.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;
    private final SkillsRepository skillsRepository;

    public LessonServiceImpl(
            LessonRepository repository,
            SkillsRepository skillsRepository
    ){
        this.repository=repository;
        this.skillsRepository=skillsRepository;
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        Set<Skills> givenSkills=lesson.getSkills();

        Set<Skills> skillsFromDb=new HashSet<>();

        for(Skills skill:givenSkills){
            Optional<Skills> foundSkill=skillsRepository.findById(skill.getId());
            foundSkill.ifPresent(skillsFromDb::add);
        }
        lesson.setSkills(skillsFromDb);

        return repository.save(lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        Optional<Lesson> lessonOptional=repository.findById(id);
        if(lessonOptional.isPresent()){
            return lessonOptional.get();
        }
        throw new CustomNotFoundException("Lesson Not found!");
    }

    @Override
    public List<Lesson> getLessonsByAuthorId(String id) {
        return repository.findAllByAuthorId(id);
    }

    @Override
    public Lesson updateLesson(Lesson lesson) {
        Optional<Lesson> lessonOptional=repository.findById(lesson.getId());
        System.out.println(lesson.toString());
        if(lessonOptional.isPresent()){
            return repository.save(lesson);
        }
        throw new CustomException("Lesson update failed!");
    }

    @Override
    public void deleteLesson(Long id) {
        Optional<Lesson> lessonOptional=repository.findById(id);
        if(lessonOptional.isPresent()){
           repository.delete(lessonOptional.get());
        }
        else{
            throw new CustomNotFoundException("Lesson Not found!");
        }
    }
}
