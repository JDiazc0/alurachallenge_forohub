package com.aluraChallenge.ForoHub.Course.Controller;

import com.aluraChallenge.ForoHub.Course.Course;
import com.aluraChallenge.ForoHub.Course.DTO.CourseDTO;
import com.aluraChallenge.ForoHub.Course.DTO.CourseListDTO;
import com.aluraChallenge.ForoHub.Course.DTO.CourseResponseDTO;
import com.aluraChallenge.ForoHub.Course.Repository.CourseRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody @Valid CourseDTO courseDTO,
                                                          UriComponentsBuilder uriComponentsBuilder){

        Course course = courseRepository.save(new Course(courseDTO));
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(course.getId(), course.getCourse_name());

        URI url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(url).body(courseResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CourseListDTO>> courseList(@PageableDefault(size = 2)Pageable pageable){
        return ResponseEntity.ok(courseRepository.findAll(pageable).map(CourseListDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> findCourse(@PathVariable Long id){
        Course course = courseRepository.getReferenceById(id);
        var courseData = new CourseResponseDTO(course.getId(), course.getCourse_name());

        return ResponseEntity.ok(courseData);
    }
}
