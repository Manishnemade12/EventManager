package com.quanti_learn.user_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String gitHub;

    @CreationTimestamp // added local date and time automatically on persist
    @Column(updatable = false) // can't be updated
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String linkedIn;
    @ManyToMany(
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }, // no cascade delete
            fetch = FetchType.EAGER // for each user, roles also will be fetched
    )
    @JoinTable(
            name = "profile_learning_goals", // table name
            joinColumns = @JoinColumn(name = "profile_id"), // current table
            inverseJoinColumns = @JoinColumn(name = "topic_id") // other table
    )
    private Set<Topic> learningGoal;

    @ManyToMany(
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }, // no cascade delete
            fetch = FetchType.EAGER // for each user, roles also will be fetched
    )
    @JoinTable(
            name = "profile_topic_interested", // table name
            joinColumns = @JoinColumn(name = "profile_id"), // current table
            inverseJoinColumns = @JoinColumn(name = "topic_id") // other table
    )
    private Set<Topic> topicInterested;

    @Column(nullable = false)
    private int currentLevel=0;

    public void addNewLearningGoal(Topic topic){
        this.learningGoal.add(topic);
    }

    public void addNewTopicInterested(Topic topic){
        this.learningGoal.add(topic);
    }

    public void increaseLevel(){
        currentLevel=currentLevel+1;
    }
}
