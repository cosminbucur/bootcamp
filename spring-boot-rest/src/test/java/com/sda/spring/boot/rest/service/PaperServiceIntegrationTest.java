package com.sda.spring.boot.rest.service;

import com.sda.spring.boot.rest.server.dto.PaperRequest;
import com.sda.spring.boot.rest.server.dto.PaperResponse;
import com.sda.spring.boot.rest.server.service.PaperAdvancedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PaperServiceIntegrationTest {

    @Autowired
//    private PaperService paperService;
    private PaperAdvancedService paperService;

    @BeforeEach
    void setUp() {
        paperService.findAll().forEach(
                paperResponse -> paperService.delete(paperResponse.getId())
        );
    }

    @Test
    void givenPaperRequest_whenSave_thenReturnPaperResponse() {
        // given
        PaperRequest request = new PaperRequest();
        request.setTitle("title");
        request.setAuthor("author");
        request.setPublished(LocalDate.of(2000, 6, 30));

        // when
        PaperResponse actual = paperService.save(request);

        PaperResponse expected = new PaperResponse();
        expected.setId(actual.getId());
        expected.setTitle("title");
        expected.setAuthor("author");
        expected.setPublished(LocalDate.of(2000, 6, 30));

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenExistingPaper_whenSave_thenThrowException() {
        // given
        // setup initial db state
        PaperRequest paperRequest = new PaperRequest();
        paperRequest.setTitle("title");
        paperRequest.setAuthor("author");
        paperRequest.setPublished(LocalDate.of(2000, 6, 30));

        paperService.save(paperRequest);

        // then
        assertThrows(RuntimeException.class,
                // when
                () -> paperService.save(paperRequest));
    }

    @Test
    void given2Papers_whenFindAll_thenReturn2Responses() {
        // given
        PaperRequest request1 = new PaperRequest();
        request1.setTitle("title1");
        request1.setAuthor("author");
        request1.setPublished(LocalDate.of(2000, 6, 30));

        PaperRequest request2 = new PaperRequest();
        request2.setTitle("title2");
        request2.setAuthor("author");
        request2.setPublished(LocalDate.of(2001, 6, 30));

        paperService.save(request1);
        paperService.save(request2);

        // when
        List<PaperResponse> actual = paperService.findAll();

        // then
        assertThat(actual).hasSize(2);
    }

    @Test
    void givenValidId_whenFindById_thenReturnPaperResponse() {
        // given
        PaperRequest paperRequest = new PaperRequest();
        paperRequest.setTitle("title");
        paperRequest.setAuthor("author");
        paperRequest.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse savedPaper = paperService.save(paperRequest);

        // when
        PaperResponse actual = paperService.findById(savedPaper.getId());

        // then
        assertThat(actual.getTitle()).isEqualTo(paperRequest.getTitle());
    }

    @Test
    void givenInvalidId_whenFindById_thenThrowException() {
        assertThrows(RuntimeException.class,
                () -> paperService.findById(1L));
    }

    @Test
    void given2PapersSameAuthor_whenFindByAuthor_thenReturn2Papers() {
        // given
        PaperRequest request1 = new PaperRequest();
        request1.setTitle("title1");
        request1.setAuthor("author");
        request1.setPublished(LocalDate.of(2000, 6, 30));

        PaperRequest request2 = new PaperRequest();
        request2.setTitle("title2");
        request2.setAuthor("author");
        request2.setPublished(LocalDate.of(2001, 6, 30));

        paperService.save(request1);
        paperService.save(request2);

        // when
        List<PaperResponse> actual = paperService.findByAuthor("author");

        // then
        assertThat(actual).hasSize(2);
    }

    @Test
    void givenPaper_whenUpdate_thenReturnUpdatedPaper() {
        // given
        PaperRequest request = new PaperRequest();
        request.setTitle("title1");
        request.setAuthor("author1");
        request.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse savedPaper = paperService.save(request);

        PaperRequest updateRequest = new PaperRequest();
        updateRequest.setTitle("title2");
        updateRequest.setAuthor("author2");
        updateRequest.setPublished(LocalDate.of(2001, 6, 30));

        // when
        PaperResponse actual = paperService.update(savedPaper.getId(), updateRequest);

        // then
        assertThat(actual.getTitle()).isEqualTo(updateRequest.getTitle());
    }

    @Test
    void givenPaper_whenDelete_thenDbIsEmpty() {
        // given
        PaperRequest request = new PaperRequest();
        request.setTitle("title");
        request.setAuthor("author");
        request.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse savedPaper = paperService.save(request);
        int sizeBefore = paperService.findAll().size();

        // when
        paperService.delete(savedPaper.getId());
        int sizeAfter = paperService.findAll().size();

        // then
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

}