package com.aluraChallenge.ForoHub.Comment.Controller;

import com.aluraChallenge.ForoHub.Comment.Comment;
import com.aluraChallenge.ForoHub.Comment.DTO.CommentDTO;
import com.aluraChallenge.ForoHub.Comment.DTO.CommentListDTO;
import com.aluraChallenge.ForoHub.Comment.DTO.CommentResponseDTO;
import com.aluraChallenge.ForoHub.Comment.DTO.UpdateDataCommentDTO;
import com.aluraChallenge.ForoHub.Comment.Repository.CommentRepository;
import com.aluraChallenge.ForoHub.Topic.Repository.TopicRepository;
import com.aluraChallenge.ForoHub.Topic.Topic;
import com.aluraChallenge.ForoHub.User.Repository.UserRepository;
import com.aluraChallenge.ForoHub.User.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentController(TopicRepository topicRepository, UserRepository userRepository, CommentRepository commentRepository){
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody @Valid CommentDTO commentDTO,
                                                            UriComponentsBuilder uriComponentsBuilder){
        User user = userRepository.findById(commentDTO.userId())
                .orElseThrow(()->new EntityNotFoundException(
                        "User with Id " + commentDTO.userId() + " not found"));

        Topic topic = topicRepository.findById(commentDTO.topicId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Topic with Id " + commentDTO.topicId() + " not found"));

        Comment savedComment = commentRepository.save(new Comment(commentDTO, user, topic));
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO(savedComment, user, topic);

        URI url = uriComponentsBuilder.path("/comments/{id}").buildAndExpand(savedComment.getId()).toUri();

        return ResponseEntity.created(url).body(commentResponseDTO);
    }

    @GetMapping("/{topicId}")
    public ResponseEntity<Page<CommentListDTO>> commentList(@PathVariable Long topicId,Pageable pageable){
        if (!topicRepository.existsById(topicId)) {
            return ResponseEntity.notFound().build();
        }

        Page<Comment> comments = commentRepository.findByTopicId(topicId, pageable);
        Page<CommentListDTO> commentListDTOS = comments.map(CommentListDTO::new);

        return ResponseEntity.ok(commentListDTOS);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CommentResponseDTO> updateComment(@RequestBody UpdateDataCommentDTO updateDataCommentDTO){
        Comment comment = commentRepository.getReferenceById(updateDataCommentDTO.id());
        comment.updateData(updateDataCommentDTO);

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment, comment.getUser(), comment.getTopic());

        return ResponseEntity.ok(commentResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroyComment(@PathVariable Long id){
        if (!commentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        commentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
