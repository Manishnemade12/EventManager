package quantiLearn.progress_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quantiLearn.progress_service.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
}
