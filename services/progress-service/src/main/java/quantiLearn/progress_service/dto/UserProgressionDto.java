package quantiLearn.progress_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProgressionDto {

    private Long id;
    private String userId;
    private Long lessonId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private StatusDto status;
}
