package com.aluraChallenge.ForoHub.Course.Repository;

import com.aluraChallenge.ForoHub.Course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
