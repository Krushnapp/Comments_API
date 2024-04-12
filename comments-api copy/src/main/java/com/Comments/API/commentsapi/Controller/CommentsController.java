package com.Comments.API.commentsapi.Controller;

import com.Comments.API.commentsapi.Entity.Comment;
import com.Comments.API.commentsapi.Repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v2/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/search")
    public List<Comment> searchComments(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "date", required = false) String dateString)  {

        if (username != null) {
            return commentRepository.findByBy(username);
        } else if (dateString != null) {
            // Parse date string into a LocalDate object
            LocalDate date = parseDate(dateString);
            return commentRepository.findByDateOfComment(date);
        } else {
            return Collections.emptyList();
        }
    }
    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        // Check if comment exists first
        Comment existingComment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found with id: " + id));

        existingComment.setBy(updatedComment.getBy());
        existingComment.setText(updatedComment.getText());
        existingComment.setDateOfComment(updatedComment.getDateOfComment());

        return commentRepository.save(existingComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private LocalDate parseDate(String dateString)  {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        return LocalDate.parse(
                dateString);
    }
}
