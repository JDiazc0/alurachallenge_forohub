package com.aluraChallenge.ForoHub.Course.DTO;

import com.aluraChallenge.ForoHub.Topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CourseDTO(
        String course_name
) {
}
