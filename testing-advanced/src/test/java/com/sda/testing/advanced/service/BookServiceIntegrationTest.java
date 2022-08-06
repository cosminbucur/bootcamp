package com.sda.testing.advanced.service;

import com.sda.testing.advanced.dto.PaperRequest;
import com.sda.testing.advanced.dto.PaperResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PaperServiceIntegrationTest {

    @Autowired
    private PaperService paperService;

    @Test
    void givenPaperRequest_whenSave_thenReturnPaperResponse() {
        // given
        PaperRequest request = new PaperRequest();
        request.setTitle("game of thrones");
        request.setAuthor("george martin");
        request.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse expected = new PaperResponse();
        expected.setId(1L);
        expected.setTitle("game of thrones");
        expected.setAuthor("george martin");
        expected.setPublished(LocalDate.of(2000, 6, 30));

        // when
        PaperResponse actual = paperService.save(request);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenExistingPaper_whenSave_thenThrowException() {
        // given
        // setup initial db state
        PaperRequest paperRequest = new PaperRequest();
        paperRequest.setTitle("game of thrones");
        paperRequest.setAuthor("george martin");
        paperRequest.setPublished(LocalDate.of(2000, 6, 30));

        paperService.save(paperRequest);

        // then
        Assertions.assertThrows(RuntimeException.class,
                // when
                () -> paperService.save(paperRequest));
    }

    @Test
    void findAll() {
    }

    @Test
    void givenValidId_whenFindById_thenReturnPaperResponse() {
        // given
        PaperRequest paperRequest = new PaperRequest();
        paperRequest.setTitle("game of thrones");
        paperRequest.setAuthor("george martin");
        paperRequest.setPublished(LocalDate.of(2000, 6, 30));

        paperService.save(paperRequest);

        // when
        PaperResponse actual = paperService.findById(1L);

        assertThat(actual.getTitle()).isEqualTo(paperRequest.getTitle());
    }

    @Test
    void givenInvalidId_whenFindById_thenThrowException() {
        Assertions.assertThrows(RuntimeException.class,
                () -> paperService.findById(1L));
    }

    @Test
    void findByAuthor() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void partialUpdate() {
    }
}