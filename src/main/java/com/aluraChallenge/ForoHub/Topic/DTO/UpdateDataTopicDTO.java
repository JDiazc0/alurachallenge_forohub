package com.aluraChallenge.ForoHub.Topic.DTO;

import com.aluraChallenge.ForoHub.Topic.Resources.TopicStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateDataTopicDTO(
        @NotNull
        Long id,
        String title,
        String message

) {
}
