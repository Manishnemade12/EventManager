package com.quantilearn.lesson_service.mapper;

import com.quantilearn.lesson_service.dto.LessonContentDto;
import com.quantilearn.lesson_service.entity.LessonContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonContentMapper {

    @Mapping(source = "lesson.id", target = "lessonId")
    LessonContentDto toDto(LessonContent lessonContent);

    @Mapping(source = "lessonId", target = "lesson.id")
    LessonContent fromDto(LessonContentDto lessonContentDto);
}
