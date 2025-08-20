package quantiLearn.notification_service.dtoService.dtoServiceImpl;

import org.springframework.stereotype.Service;
import quantiLearn.notification_service.dto.NotificationLogDto;
import quantiLearn.notification_service.dtoService.NotificationLogsDtoService;
import quantiLearn.notification_service.mapper.NotificationLogMapper;
import quantiLearn.notification_service.service.NotificationLogsService;

@Service
public class NotificationLogsDtoServiceImpl implements NotificationLogsDtoService {

    private final NotificationLogsService service;
    private final NotificationLogMapper mapper;

    public NotificationLogsDtoServiceImpl(
            NotificationLogsService service,
            NotificationLogMapper mapper
    )
    {
        this.service=service;
        this.mapper=mapper;
    }

    @Override
    public NotificationLogDto createLog(NotificationLogDto notificationLogDto) {
        notificationLogDto.setId(null);
        return mapper.toDto(service.createLog(mapper.fromDto(notificationLogDto)));
    }

    @Override
    public void markAsRead(Long id) {
        service.markAsRead(id);
    }

    @Override
    public void deleteLog(Long id) {
        service.deleteLog(id);
    }
}
