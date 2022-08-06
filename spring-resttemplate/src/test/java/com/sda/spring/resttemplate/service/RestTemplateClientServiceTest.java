package com.sda.spring.resttemplate.service;

import com.sda.spring.resttemplate.dto.PaperRequest;
import com.sda.spring.resttemplate.dto.PaperResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestTemplateClientServiceTest {

    @Autowired
    private RestTemplateClientService service;

    @BeforeEach
    void setUp() {
        service.findAll().forEach(
                paperResponse -> service.delete(paperResponse.getId())
        );
    }

    @Test
    void save() {
        // given
        PaperRequest paperRequest = new PaperRequest();
        paperRequest.setTitle("title");
        paperRequest.setAuthor("author");

        // when
        PaperResponse savedPaper = service.save(paperRequest);

        // then
        assertThat(savedPaper).isNotNull();
    }

    @Test
    void findAll() {
        // given
        PaperRequest paperRequest1 = new PaperRequest();
        paperRequest1.setTitle("title1");
        paperRequest1.setAuthor("author");

        PaperRequest paperRequest2 = new PaperRequest();
        paperRequest2.setTitle("title2");
        paperRequest2.setAuthor("author");

        PaperResponse savedPaper1 = service.save(paperRequest1);
        PaperResponse savedPaper2 = service.save(paperRequest2);

        // when
        List<PaperResponse> actual = service.findAll();

        // then
        assertThat(actual).hasSize(2);
    }

    @Test
    void findById() {
        // given
        PaperRequest paperRequest = new PaperRequest();
        paperRequest.setTitle("title");
        paperRequest.setAuthor("author");

        PaperResponse savedPaper = service.save(paperRequest);

        // when
        PaperResponse actual = service.findById(savedPaper.getId());

        // then
        assertThat(actual.getTitle()).isEqualTo("title");
    }

    @Test
    void update() {
        // given
        PaperRequest paperRequest = new PaperRequest();
        paperRequest.setTitle("title");
        paperRequest.setAuthor("author");

        PaperResponse savedPaper = service.save(paperRequest);

        PaperRequest updateRequest = new PaperRequest();
        updateRequest.setTitle("title2");
        updateRequest.setAuthor("author2");

        // when
        service.update(savedPaper.getId(), updateRequest);
        PaperResponse updatedPaper = service.findById(savedPaper.getId());

        // then
        assertThat(updatedPaper.getTitle()).isEqualTo("title2");
    }

    @Test
    void delete() {
        PaperRequest paperRequest = new PaperRequest();
        paperRequest.setTitle("title");
        paperRequest.setAuthor("author");
        PaperResponse savedPaper = service.save(paperRequest);

        // when
        service.delete(savedPaper.getId());

        assertThat(service.findAll()).isEmpty();
    }
}