package com.quantilearn.lesson_service.mapper;

import com.quantilearn.lesson_service.dto.SkillsDto;
import com.quantilearn.lesson_service.entity.Skills;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillsMapper {
    SkillsDto toDto(Skills skills);
    Skills fromDto(SkillsDto skillsDto);
}
