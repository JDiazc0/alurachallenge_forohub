package com.aluraChallenge.ForoHub.Topic.DTO;

import com.aluraChallenge.ForoHub.Course.Course;
import com.aluraChallenge.ForoHub.Topic.Resources.TopicStatus;
import com.aluraChallenge.ForoHub.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record TopicDTO(
        String title,
        String message,
        TopicStatus status,
        Long userId,
        Long courseId
) {
}
