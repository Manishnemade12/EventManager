package com.quantilearn.shareddtos.lesson_service;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    private Long id;
    private String title;
    private Set<SkillsDto> skills;
    private String authorId;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isPublic;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<LessonContentDto> contents;
}