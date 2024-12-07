package com.aluraChallenge.ForoHub.Comment.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties()
public record CommentDTO(
        String text,
        Long userId,
        Long topicId
) {
}
