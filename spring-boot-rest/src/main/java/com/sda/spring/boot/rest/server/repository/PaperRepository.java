package com.sda.spring.boot.rest.server.repository;

import com.sda.spring.boot.rest.server.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {

    List<Paper> findByAuthor(String author);

    Paper findByTitle(String title);
}
