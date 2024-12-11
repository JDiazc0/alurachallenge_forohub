package com.aluraChallenge.ForoHub.Topic;

import com.aluraChallenge.ForoHub.Comment.Comment;
import com.aluraChallenge.ForoHub.Course.Course;
import com.aluraChallenge.ForoHub.Topic.DTO.TopicDTO;
import com.aluraChallenge.ForoHub.Topic.DTO.UpdateDataTopicDTO;
import com.aluraChallenge.ForoHub.Topic.Resources.TopicStatus;
import com.aluraChallenge.ForoHub.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creation_date;
    @Enumerated(EnumType.STRING)
    private TopicStatus status;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id") 
    private Course course;

    public Topic(TopicDTO topicData, User user, Course course) {
        this.title = topicData.title();
        this.message = topicData.message();
        this.creation_date = LocalDateTime.now();
        this.status = topicData.status();
        this.user = user;
        this.course = course;
    }

    public void updateData(UpdateDataTopicDTO updateDataTopicDTO) {
        if(updateDataTopicDTO.title() != null){
            this.title = updateDataTopicDTO.title();
        }
        if(updateDataTopicDTO.message() != null){
            this.message = updateDataTopicDTO.message();
        }
    }

    public void deactiveTopic() {
        this.status = TopicStatus.INACTIVE;
    }
}
