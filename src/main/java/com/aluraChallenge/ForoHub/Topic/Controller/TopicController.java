package com.aluraChallenge.ForoHub.Topic.Controller;

import com.aluraChallenge.ForoHub.Course.Course;
import com.aluraChallenge.ForoHub.Course.Repository.CourseRepository;
import com.aluraChallenge.ForoHub.Topic.DTO.TopicDTO;
import com.aluraChallenge.ForoHub.Topic.Repository.TopicRepository;
import com.aluraChallenge.ForoHub.Topic.Topic;
import com.aluraChallenge.ForoHub.User.Repository.UserRepository;
import com.aluraChallenge.ForoHub.User.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TopicController(TopicRepository topicRepository, UserRepository userRepository, CourseRepository courseRepository){
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public void createTopic(@RequestBody @Valid TopicDTO topicDTO){
        User user = userRepository.findById(topicDTO.userId())
                        .orElseThrow(EntityNotFoundException::new);
        Course course = courseRepository.findById(topicDTO.courseId())
                        .orElseThrow(EntityNotFoundException::new);
        topicRepository.save(new Topic(topicDTO, user, course));
    }

}
