package com.sda.testing.advanced.model;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "Paper")
@Table(name = "paper")
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @PastOrPresent
    private LocalDate published;

    public Paper() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long newId) {
        this.id = newId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paper paper = (Paper) o;
        return Objects.equals(id, paper.id) &&
                Objects.equals(title, paper.title) &&
                Objects.equals(author, paper.author) &&
                Objects.equals(published, paper.published);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, published);
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", published=" + published +
                '}';
    }
}
