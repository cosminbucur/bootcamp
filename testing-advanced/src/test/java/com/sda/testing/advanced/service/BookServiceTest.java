package com.sda.testing.advanced.service;

import com.sda.testing.advanced.dto.PaperMapper;
import com.sda.testing.advanced.dto.PaperRequest;
import com.sda.testing.advanced.dto.PaperResponse;
import com.sda.testing.advanced.model.Paper;
import com.sda.testing.advanced.repository.PaperRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaperServiceTest {

    @Mock
    private PaperRepository paperRepository;

    @Mock
    private PaperMapper paperMapper;

    @InjectMocks
    private PaperService paperService;

    @Test
    void givenPaperRequest_whenSave_thenReturnPaperResponse() {
        // given
        PaperRequest request = new PaperRequest();
        request.setTitle("game of thrones");
        request.setAuthor("george martin");
        request.setPublished(LocalDate.of(2000, 6, 30));

        Paper mockPaper = new Paper();
        mockPaper.setId(1L);
        mockPaper.setTitle("game of thrones");
        mockPaper.setAuthor("george martin");
        mockPaper.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse expected = new PaperResponse();
        expected.setId(1L);
        expected.setTitle("game of thrones");
        expected.setAuthor("george martin");
        expected.setPublished(LocalDate.of(2000, 6, 30));

        when(paperRepository.findByTitle(anyString()))
                .thenReturn(mockPaper);
        when(paperMapper.toEntity(any(PaperRequest.class)))
                .thenReturn(mockPaper);
        when(paperRepository.save(any(Paper.class)))
                .thenReturn(mockPaper);
        when(paperMapper.toDto(any(Paper.class)))
                .thenReturn(expected);

        // when
        PaperResponse actual = paperService.save(request);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenExistingPaper_whenSave_thenThrowException() {
        // given
        PaperRequest paperRequest = new PaperRequest();
        paperRequest.setTitle("game of thrones");
        paperRequest.setAuthor("george martin");
        paperRequest.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse expected = new PaperResponse();
        expected.setId(1L);
        expected.setTitle("game of thrones");
        expected.setAuthor("george martin");
        expected.setPublished(LocalDate.of(2000, 6, 30));

        when(paperRepository.findByTitle(anyString()))
                .thenReturn(null);

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
        Paper paper = new Paper();
        paper.setId(1L);
        paper.setTitle("game of thrones");
        paper.setAuthor("george martin");
        paper.setPublished(LocalDate.of(2000, 6, 30));

        Optional<Paper> mockPaper = Optional.of(paper);

        PaperResponse mockResponse = new PaperResponse();
        mockResponse.setId(2L);
        mockResponse.setTitle("game of thrones2");
        mockResponse.setAuthor("george martin2");
        mockResponse.setPublished(LocalDate.of(2000, 6, 30));

        when(paperRepository.findById(anyLong()))
                .thenReturn(mockPaper);
        when(paperMapper.toDto(any(Paper.class)))
                .thenReturn(mockResponse);

        // when
        PaperResponse actual = paperService.findById(1L);

        assertThat(actual).isEqualTo(mockResponse);
    }

    @Test
    void givenInvalidId_whenFindById_thenThrowException() {
        // define test scenario
        // identify what needs to be mocked
        // objects with methods that are being called

        // given
        // mock objects
        // mock return objects

        // when
        // call method under test

        // then
        // assert

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