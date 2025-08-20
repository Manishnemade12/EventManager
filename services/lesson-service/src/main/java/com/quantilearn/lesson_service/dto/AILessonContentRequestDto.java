package com.quantilearn.lesson_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AILessonContentRequestDto {
    private String title;
    private String description;
    private List<String> skills;
}
