package com.aluraChallenge.ForoHub.Comment.DTO;

import com.aluraChallenge.ForoHub.Comment.Comment;
import com.aluraChallenge.ForoHub.User.DTO.UserResponseDTO;
import com.aluraChallenge.ForoHub.User.User;

public record CommentListDTO(
        Long id,
        String text,
        UserResponseDTO user
) {
    public CommentListDTO(Comment comment){
        this(
                comment.getId(),
                comment.getText(),
                new UserResponseDTO(comment.getUser())
        );
    }
}
