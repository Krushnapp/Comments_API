package com.Comments.API.commentsapi.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String by;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDate dateOfComment;

    public Comment(String johnDoe,String s,LocalDate of) {

    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", by='" + by + '\'' +
                ", text='" + text + '\'' +
                ", dateOfComment=" + dateOfComment +
                '}';
    }
// Getters and Setters omitted for brevity

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDateOfComment() {
        return dateOfComment;
    }

    public void setDateOfComment(LocalDate dateOfComment) {
        this.dateOfComment = dateOfComment;
    }
}

