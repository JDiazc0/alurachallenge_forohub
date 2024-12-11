package com.aluraChallenge.ForoHub.Topic.DTO;

import com.aluraChallenge.ForoHub.Course.Course;
import com.aluraChallenge.ForoHub.Course.DTO.CourseResponseDTO;
import com.aluraChallenge.ForoHub.Topic.Resources.TopicStatus;
import com.aluraChallenge.ForoHub.Topic.Topic;
import com.aluraChallenge.ForoHub.User.DTO.UserResponseDTO;
import com.aluraChallenge.ForoHub.User.User;

public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        TopicStatus status,
        UserResponseDTO user,
        CourseResponseDTO course
) {
    public TopicResponseDTO(Topic topic, User user, Course course){
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                new UserResponseDTO(user),
                new CourseResponseDTO(course)
        );
    }
}
