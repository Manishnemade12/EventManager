package com.quantilearn.lesson_service.service.serviceImpl;

import com.quantilearn.lesson_service.dto.*;
import com.quantilearn.lesson_service.service.AIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AIServiceImpl implements AIService {

    private final ChatClient chatClient;

    public AIServiceImpl(
           @Qualifier("openAiChatClient") ChatClient chatClient
    ){
        this.chatClient=chatClient;
    }

    @Override
    public AILessonResponse getLessonContent(AILessonContentRequestDto lesson) {
        String lessonPrompt= " I want to generate lesson content for"+lesson.getTitle()+
                ". "+"It should have"+lesson.getDescription()+". "+ "It must cover skills, which includes "+
                lesson.getSkills().toString()+" and each skill must be in a separate lesson content";
        return chatClient.prompt()
                .user(lessonPrompt)
                .call()
                .entity(AILessonResponse.class);
    }
}
