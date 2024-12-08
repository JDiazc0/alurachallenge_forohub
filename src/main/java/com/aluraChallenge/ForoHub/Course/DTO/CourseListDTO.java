package com.aluraChallenge.ForoHub.Course.DTO;

import com.aluraChallenge.ForoHub.Course.Course;

public record CourseListDTO(
        Long id,
        String course_name
) {
    public CourseListDTO(Course course){
        this(course.getId(), course.getCourse_name());
    }
}
