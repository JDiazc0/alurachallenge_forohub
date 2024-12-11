package com.aluraChallenge.ForoHub.Topic.DTO;

import com.aluraChallenge.ForoHub.Topic.Resources.TopicStatus;
import com.aluraChallenge.ForoHub.Topic.Topic;

public record TopicListDTO(
        Long id,
        String title,
        String message,
        TopicStatus status
) {
    public TopicListDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus()
        );
    }
}
