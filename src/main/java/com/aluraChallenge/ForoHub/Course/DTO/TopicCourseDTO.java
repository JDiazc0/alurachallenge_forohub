package com.aluraChallenge.ForoHub.Course.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TopicCourseDTO(
        Long id
) {
}
