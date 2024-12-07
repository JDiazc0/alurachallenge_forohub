package com.aluraChallenge.ForoHub.Course;

import com.aluraChallenge.ForoHub.Course.DTO.CourseDTO;
import com.aluraChallenge.ForoHub.Topic.Topic;
import com.aluraChallenge.ForoHub.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String course_name;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Topic> topics;
    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<User> users;


    public Course(CourseDTO courseDTO){
        this.course_name = courseDTO.course_name();
    }

}
