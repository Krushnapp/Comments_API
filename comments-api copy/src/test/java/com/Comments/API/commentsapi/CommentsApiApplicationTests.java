package com.Comments.API.commentsapi;

import com.Comments.API.commentsapi.Controller.CommentsController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Collections;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.Comments.API.commentsapi.Entity.Comment;
import com.Comments.API.commentsapi.Repo.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class CommentsApiApplicationTests {

	@Autowired
	private CommentsController commentsController;

	@MockBean
	private CommentRepository commentRepository;

	private List<Comment> mockComments;

	@BeforeEach
	public void setup() {
		mockComments = Collections.singletonList(
				new Comment("John Doe", "This is a sample comment.", LocalDate.of(2024, 4, 11))
		);
	}

	@Test
	public void testGetAllComments() {
		when(commentRepository.findAll()).thenReturn(mockComments);

		List<Comment> allComments = commentsController.getAllComments();

		assertThat(allComments).isEqualTo(mockComments);
		verify(commentRepository, times(1)).findAll();
	}

	@Test
	public void testSearchCommentsByUsername() {
		String username = "John Doe";
		when(commentRepository.findByBy(username)).thenReturn(mockComments);

		List<Comment> commentsByUsername = commentsController.searchComments(username, null);

		assertThat(commentsByUsername).isEqualTo(mockComments);
		verify(commentRepository, times(1)).findByBy(username);
	}

	@Test
	public void testSearchCommentsByDate() {
		LocalDate date = LocalDate.of(2024, 4, 11);
		when(commentRepository.findByDateOfComment(date)).thenReturn(mockComments);

		List<Comment> commentsByDate = commentsController.searchComments(null, "2024-04-11");

		assertThat(commentsByDate).isEqualTo(mockComments);
		verify(commentRepository, times(1)).findByDateOfComment(date);
	}


}

