package com.aluraChallenge.ForoHub.Course.DTO;

import com.aluraChallenge.ForoHub.Course.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CourseResponseDTO(
        Long id,
        String course_name
) {
    public CourseResponseDTO(Course course){
        this(
                course.getId(),
                course.getCourse_name()
        );
    }
}
