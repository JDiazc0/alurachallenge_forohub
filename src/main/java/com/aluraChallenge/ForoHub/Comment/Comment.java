package com.aluraChallenge.ForoHub.Comment;

import com.aluraChallenge.ForoHub.Comment.DTO.CommentDTO;
import com.aluraChallenge.ForoHub.Comment.DTO.UpdateDataCommentDTO;
import com.aluraChallenge.ForoHub.Topic.Topic;
import com.aluraChallenge.ForoHub.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public Comment(CommentDTO commentDTO, User user, Topic topic){
        this.text = commentDTO.text();
        this.user = user;
        this.topic = topic;
    }

    public void updateData(UpdateDataCommentDTO updateDataCommentDTO) {
        if(updateDataCommentDTO.text() != null){
            this.text = updateDataCommentDTO.text();
        }
    }
}
