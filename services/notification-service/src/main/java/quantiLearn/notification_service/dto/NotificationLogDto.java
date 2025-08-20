package quantiLearn.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationLogDto {
    private Long id;
    private String userId;
    private String content;
    private boolean isRead;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CategoryDto category;
}
