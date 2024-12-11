package com.aluraChallenge.ForoHub.Topic.Controller;

import com.aluraChallenge.ForoHub.Course.Course;
import com.aluraChallenge.ForoHub.Course.Repository.CourseRepository;
import com.aluraChallenge.ForoHub.Topic.DTO.TopicDTO;
import com.aluraChallenge.ForoHub.Topic.DTO.TopicListDTO;
import com.aluraChallenge.ForoHub.Topic.DTO.TopicResponseDTO;
import com.aluraChallenge.ForoHub.Topic.DTO.UpdateDataTopicDTO;
import com.aluraChallenge.ForoHub.Topic.Repository.TopicRepository;
import com.aluraChallenge.ForoHub.Topic.Resources.TopicStatus;
import com.aluraChallenge.ForoHub.Topic.Topic;
import com.aluraChallenge.ForoHub.User.Repository.UserRepository;
import com.aluraChallenge.ForoHub.User.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<TopicResponseDTO> createTopic(@RequestBody @Valid TopicDTO topicDTO,
                                                        UriComponentsBuilder uriComponentsBuilder){
        User user = userRepository.findById(topicDTO.userId())
                        .orElseThrow(()->new EntityNotFoundException(
                                "User with Id " + topicDTO.userId() + " not found"));
        Course course = courseRepository.findById(topicDTO.courseId())
                        .orElseThrow(()->new EntityNotFoundException(
                                "Course with Id " + topicDTO.courseId() + " not found"));

        Topic savedTopic = topicRepository.save(new Topic(topicDTO, user, course));
        TopicResponseDTO topicResponseDTO = new TopicResponseDTO(savedTopic, user, course);

        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(savedTopic.getId()).toUri();

        return ResponseEntity.created(url).body(topicResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<TopicListDTO>> topicList(@PageableDefault(size = 5)Pageable pageable){
        return ResponseEntity.ok(topicRepository.findByStatus(TopicStatus.ACTIVE, pageable).map(TopicListDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> findTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        var topicData = new TopicResponseDTO(topic, topic.getUser(), topic.getCourse());

        return ResponseEntity.ok(topicData);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> updateTopic(@RequestBody @Valid UpdateDataTopicDTO updateDataTopicDTO){
        Topic topic = topicRepository.getReferenceById(updateDataTopicDTO.id());
        topic.updateData(updateDataTopicDTO);

        TopicResponseDTO topicResponseDTO = new TopicResponseDTO(topic, topic.getUser(), topic.getCourse());

        return ResponseEntity.ok(topicResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deactiveTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        topic.deactiveTopic();

        return ResponseEntity.noContent().build();
    }

}
