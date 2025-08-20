package quantiLearn.notification_service.service.serviceImpl;

import org.springframework.stereotype.Service;
import quantiLearn.notification_service.entity.NotificationLog;
import quantiLearn.notification_service.exception.exceptions.CustomNotFoundException;
import quantiLearn.notification_service.repository.NotificationLogsRepository;
import quantiLearn.notification_service.service.NotificationLogsService;

import java.util.Optional;

@Service
public class NotificationLogsServiceImpl implements NotificationLogsService {

    private final NotificationLogsRepository repository;

    public NotificationLogsServiceImpl(
            NotificationLogsRepository repository
    ){
        this.repository=repository;
    }

    @Override
    public NotificationLog createLog(NotificationLog notificationLog) {
        return repository.save(notificationLog);
    }

    @Override
    public void markAsRead(Long id) {
        Optional<NotificationLog> notificationLogOptional=repository.findById(id);
        if(notificationLogOptional.isPresent()){
            NotificationLog notificationLog=notificationLogOptional.get();
            notificationLog.setRead(true);
            repository.save(notificationLog);
        }
        else{
            throw new CustomNotFoundException("Log not found");
        }
    }

    @Override
    public void deleteLog(Long id) {
        Optional<NotificationLog> notificationLogOptional=repository.findById(id);
        if(notificationLogOptional.isPresent()){
            NotificationLog notificationLog=notificationLogOptional.get();
            notificationLog.setRead(true);
            repository.save(notificationLog);
        }
        else{
            throw new CustomNotFoundException("Log not found");
        }
    }
}
