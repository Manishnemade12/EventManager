package quantiLearn.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quantiLearn.notification_service.entity.NotificationLog;

@Repository
public interface NotificationLogsRepository extends JpaRepository<NotificationLog,Long> {
}
