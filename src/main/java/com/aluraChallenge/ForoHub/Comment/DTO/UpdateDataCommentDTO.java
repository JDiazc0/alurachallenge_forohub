package com.aluraChallenge.ForoHub.Comment.DTO;

import jakarta.validation.constraints.NotNull;

public record UpdateDataCommentDTO(
        @NotNull
        Long id,
        String text
) {
}
