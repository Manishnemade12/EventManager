package com.quantilearn.lesson_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String authorId;

    @ManyToMany(
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }, // no cascade delete
            fetch = FetchType.EAGER // for each user, roles also will be fetched
    )
    @JoinTable(
            name = "lesson_skills", // table name
            joinColumns = @JoinColumn(name = "lesson_id"), // current table
            inverseJoinColumns = @JoinColumn(name = "skill_id") // other table
    )
    private Set<Skills> skills;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Column(nullable = false)
    private boolean isPublic;

    @CreationTimestamp // added local date and time automatically on persist
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(
            cascade = CascadeType.ALL,
        fetch = FetchType.EAGER,
            mappedBy = "lesson"
    )
    private List<LessonContent> contents;
}
