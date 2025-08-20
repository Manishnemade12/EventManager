package com.quanti_learn.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String gitHub;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String linkedIn;
    private Set<TopicDto> learningGoal;
    private Set<TopicDto> topicInterested;
    private int currentLevel=0;
}
