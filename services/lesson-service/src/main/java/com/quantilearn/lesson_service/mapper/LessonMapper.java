package com.quantilearn.lesson_service.mapper;

import com.quantilearn.lesson_service.dto.LessonDto;
import com.quantilearn.lesson_service.entity.Lesson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonDto toDto(Lesson lesson);
    Lesson fromDto(LessonDto lessonDto);
}
