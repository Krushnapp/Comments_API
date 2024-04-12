package com.Comments.API.commentsapi.Repo;

import com.Comments.API.commentsapi.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBy(String username);

    List<Comment> findByDateOfComment(LocalDate date);


}

