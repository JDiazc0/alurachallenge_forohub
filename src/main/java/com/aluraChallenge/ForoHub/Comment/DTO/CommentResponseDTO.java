package com.aluraChallenge.ForoHub.Comment.DTO;


import com.aluraChallenge.ForoHub.Comment.Comment;
import com.aluraChallenge.ForoHub.Topic.DTO.TopicListDTO;
import com.aluraChallenge.ForoHub.Topic.DTO.TopicResponseDTO;
import com.aluraChallenge.ForoHub.Topic.Topic;
import com.aluraChallenge.ForoHub.User.DTO.UserResponseDTO;
import com.aluraChallenge.ForoHub.User.User;

public record CommentResponseDTO(
        Long id,
        String text,
        UserResponseDTO user,
        TopicListDTO topic
) {
    public CommentResponseDTO(Comment comment, User user, Topic topic){
        this(
                comment.getId(),
                comment.getText(),
                new UserResponseDTO(user),
                new TopicListDTO(topic)
        );
    }
}
