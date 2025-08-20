package quantiLearn.notification_service.mapper;

import org.mapstruct.Mapper;
import quantiLearn.notification_service.dto.NotificationLogDto;
import quantiLearn.notification_service.entity.NotificationLog;

@Mapper(componentModel = "spring")
public interface NotificationLogMapper {
    NotificationLog fromDto(NotificationLogDto notificationLogDto);
    NotificationLogDto toDto(NotificationLog notificationLog);
}
