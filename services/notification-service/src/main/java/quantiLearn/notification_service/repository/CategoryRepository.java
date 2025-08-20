package quantiLearn.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quantiLearn.notification_service.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
