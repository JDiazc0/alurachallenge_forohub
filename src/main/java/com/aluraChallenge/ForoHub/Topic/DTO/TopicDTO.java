package com.aluraChallenge.ForoHub.Topic.DTO;

import com.aluraChallenge.ForoHub.Topic.Resources.TopicStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@JsonIgnoreProperties(ignoreUnknown = true)
public record TopicDTO(
        @NotBlank
        String title,
        String message,
        @NotNull
        TopicStatus status,

        Long userId,

        Long courseId
) {
}
