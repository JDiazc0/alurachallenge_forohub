package com.aluraChallenge.ForoHub.Topic.Controller;

import com.aluraChallenge.ForoHub.Topic.DTO.TopicDTO;
import com.aluraChallenge.ForoHub.Topic.Repository.TopicRepository;
import com.aluraChallenge.ForoHub.Topic.Topic;
import com.aluraChallenge.ForoHub.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;



}
