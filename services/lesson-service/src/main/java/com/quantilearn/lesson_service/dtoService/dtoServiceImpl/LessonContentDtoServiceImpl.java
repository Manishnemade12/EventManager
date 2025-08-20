package com.quantilearn.lesson_service.dtoService.dtoServiceImpl;

import com.quantilearn.lesson_service.dto.*;
import com.quantilearn.lesson_service.dtoService.LessonContentDtoService;
import com.quantilearn.lesson_service.mapper.LessonContentMapper;
import com.quantilearn.lesson_service.service.AIService;
import com.quantilearn.lesson_service.service.LessonContentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonContentDtoServiceImpl implements LessonContentDtoService {

    private final LessonContentService service;
    private final LessonContentMapper mapper;
    private final AIService aiService;

    public LessonContentDtoServiceImpl(
            LessonContentService service,
            LessonContentMapper mapper,
            AIService aiService
    ){
        this.service=service;
        this.mapper=mapper;
        this.aiService=aiService;
    }

    @Override
    public LessonContentDto createLessonContent(LessonContentDto lessonContent) {
        lessonContent.setId(null);
        return mapper.toDto(service.createLessonContent(mapper.fromDto(lessonContent)));
    }

    @Override
    public LessonContentDto getLessonContentById(Long id) {
        return mapper.toDto(service.getLessonContentById(id));
    }

    @Override
    public List<LessonContentDto> getLessonContentsByLessonId(Long id) {
        return service.getLessonContentsByLessonId(id).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public LessonContentDto updateLessonContent(LessonContentDto lessonContent) {
        return mapper.toDto(service.updateLessonContent(mapper.fromDto(lessonContent)));
    }

    @Override
    public void deleteLessonContent(Long id) {
        service.deleteLessonContent(id);
    }

    @Override
    public AILessonResponse generateLessonContentWithAI(AILessonContentRequestDto aiLessonContentRequestDto) {
        return aiService.getLessonContent(aiLessonContentRequestDto);
    }

    @Override
    public void saveAILessonContent(Long id, AILessonResponse aiLessonResponse) {
        List<AILessonContentDto> aiLessonContentDtoList=aiLessonResponse.lessonContents();

        aiLessonContentDtoList.forEach((aiLessonContentDto -> {
            LessonContentDto lessonContentDto=LessonContentDto.builder()
                    .lessonId(id)
                    .title(aiLessonContentDto.getTitle())
                    .description(aiLessonContentDto.getDescription())
                    .orderNumber(aiLessonContentDto.getOrderNumber())
                    .build();
            createLessonContent(lessonContentDto);
        }));
    }
}
