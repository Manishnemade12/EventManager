package quantiLearn.progress_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quantiLearn.progress_service.entity.UserProgression;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProgressionRepository extends JpaRepository<UserProgression,Long> {
    List<UserProgression> findAllByUserId(String id);

    Optional<UserProgression> findByUserId(String id);

    void deleteAllByUserId(String id);
}
