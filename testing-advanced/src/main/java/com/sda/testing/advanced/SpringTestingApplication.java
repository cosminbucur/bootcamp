package com.sda.testing.advanced;

import com.sda.testing.advanced.model.Paper;
import com.sda.testing.advanced.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SpringTestingApplication {

    @Autowired
    private PaperRepository paperRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringTestingApplication.class);
    }

    //    @Bean
    CommandLineRunner data() {
        return args -> paperRepository.saveAll(createPapers());
    }

    private List<Paper> createPapers() {
        Paper paper = new Paper();
        paper.setTitle("Game of thrones");
        paper.setAuthor("George Martin");
        paper.setPublished(LocalDate.now());
        return Collections.singletonList(paper);
    }
}
