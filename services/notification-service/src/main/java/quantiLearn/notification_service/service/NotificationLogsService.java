package quantiLearn.notification_service.service;

import quantiLearn.notification_service.entity.NotificationLog;

public interface NotificationLogsService {
    NotificationLog createLog(NotificationLog notificationLog);
    void markAsRead(Long id);
    void deleteLog(Long id);
}
