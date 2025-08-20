package quantiLearn.notification_service.dtoService;

import quantiLearn.notification_service.dto.NotificationLogDto;

public interface NotificationLogsDtoService {
    NotificationLogDto createLog(NotificationLogDto notificationLogDto);
    void markAsRead(Long id);
    void deleteLog(Long id);
}
