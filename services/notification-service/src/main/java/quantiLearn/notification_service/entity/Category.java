package quantiLearn.notification_service.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @OneToMany(
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }, // no cascade delete
            fetch = FetchType.LAZY, // for each user, roles also will be fetched
            mappedBy = "category"
    )
    private List<NotificationLog> notificationLogs;
}
