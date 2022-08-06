package com.sda.spring.boot.rest.service;

import com.sda.spring.boot.rest.server.dto.PaperMapper;
import com.sda.spring.boot.rest.server.dto.PaperRequest;
import com.sda.spring.boot.rest.server.dto.PaperResponse;
import com.sda.spring.boot.rest.server.model.Paper;
import com.sda.spring.boot.rest.server.repository.PaperRepository;
import com.sda.spring.boot.rest.server.service.PaperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
        request.setTitle("title");
        request.setAuthor("author");
        request.setPublished(LocalDate.of(2000, 6, 30));

        Paper mockPaper = new Paper();
        mockPaper.setId(1L);
        mockPaper.setTitle("title");
        mockPaper.setAuthor("author");
        mockPaper.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse expected = new PaperResponse();
        expected.setId(1L);
        expected.setTitle("title");
        expected.setAuthor("author");
        expected.setPublished(LocalDate.of(2000, 6, 30));

        when(paperRepository.findByTitle(anyString()))
                .thenReturn(null);
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
        paperRequest.setTitle("title");
        paperRequest.setAuthor("author");
        paperRequest.setPublished(LocalDate.of(2000, 6, 30));

        Paper mockPaper = new Paper();
        mockPaper.setId(1L);
        mockPaper.setTitle("title");
        mockPaper.setAuthor("author");
        mockPaper.setPublished(LocalDate.of(2000, 6, 30));

        when(paperRepository.findByTitle(anyString()))
                .thenReturn(mockPaper);

        // then
        assertThrows(RuntimeException.class,
                // when
                () -> paperService.save(paperRequest));
    }

    @Test
    void given2Papers_whenFindAll_thenReturn2Responses() {
        // given
        Paper mockPaper1 = new Paper();
        mockPaper1.setTitle("title1");
        mockPaper1.setAuthor("author");
        mockPaper1.setPublished(LocalDate.of(2000, 6, 30));

        Paper mockPaper2 = new Paper();
        mockPaper2.setTitle("title2");
        mockPaper2.setAuthor("author");
        mockPaper2.setPublished(LocalDate.of(2001, 6, 30));

        PaperResponse mockResponse1 = new PaperResponse();
        mockResponse1.setTitle("title1");
        mockResponse1.setAuthor("author");
        mockResponse1.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse mockResponse2 = new PaperResponse();
        mockResponse2.setTitle("title2");
        mockResponse2.setAuthor("author");
        mockResponse2.setPublished(LocalDate.of(2001, 6, 30));

        when(paperRepository.findAll())
                .thenReturn(Arrays.asList(mockPaper1, mockPaper2));
        when(paperMapper.toDto(anyList()))
                .thenReturn(List.of(mockResponse1, mockResponse2));

        // when
        List<PaperResponse> actual = paperService.findAll();

        // then
        assertThat(actual).hasSize(2);
    }

    @Test
    void givenValidId_whenFindById_thenReturnPaperResponse() {
        // given
        Paper paper = new Paper();
        paper.setId(1L);
        paper.setTitle("title");
        paper.setAuthor("author");
        paper.setPublished(LocalDate.of(2000, 6, 30));

        Optional<Paper> mockPaper = Optional.of(paper);

        PaperResponse mockResponse = new PaperResponse();
        mockResponse.setId(2L);
        mockResponse.setTitle("title2");
        mockResponse.setAuthor("author2");
        mockResponse.setPublished(LocalDate.of(2000, 6, 30));

        when(paperRepository.findById(anyLong()))
                .thenReturn(mockPaper);
        when(paperMapper.toDto(any(Paper.class)))
                .thenReturn(mockResponse);

        // when
        PaperResponse actual = paperService.findById(1L);

        // then
        assertThat(actual).isEqualTo(mockResponse);
    }

    @Test
    void givenInvalidId_whenFindById_thenThrowException() {
        assertThrows(RuntimeException.class,
                () -> paperService.findById(1L));
    }

    @Test
    void findByAuthor() {
        // given
        Paper paper1 = new Paper();
        paper1.setTitle("title1");
        paper1.setAuthor("author");
        paper1.setPublished(LocalDate.of(2000, 6, 30));

        Paper paper2 = new Paper();
        paper2.setTitle("title2");
        paper2.setAuthor("author");
        paper2.setPublished(LocalDate.of(2001, 6, 30));

        PaperResponse mockResponse1 = new PaperResponse();
        mockResponse1.setId(1L);
        mockResponse1.setTitle("title2");
        mockResponse1.setAuthor("author2");
        mockResponse1.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse mockResponse2 = new PaperResponse();
        mockResponse2.setId(2L);
        mockResponse2.setTitle("title");
        mockResponse2.setAuthor("author");
        mockResponse2.setPublished(LocalDate.of(2001, 6, 30));

        when(paperRepository.findByAuthor(anyString()))
                .thenReturn(List.of(paper1, paper2));
        when(paperMapper.toDto(anyList()))
                .thenReturn(List.of(mockResponse1, mockResponse2));

        // when
        List<PaperResponse> actual = paperService.findByAuthor("author");

        // then
        assertThat(actual).hasSize(2);
    }

    @Test
    void givenPaper_whenUpdate_thenReturnUpdatedPaper() {
        // given
        Paper paper = new Paper();
        paper.setId(1L);
        paper.setTitle("title");
        paper.setAuthor("author");
        paper.setPublished(LocalDate.of(2000, 6, 30));

        Optional<Paper> mockPaper = Optional.of(paper);

        PaperResponse mockResponse = new PaperResponse();
        mockResponse.setId(1L);
        mockResponse.setTitle("title2");
        mockResponse.setAuthor("author2");
        mockResponse.setPublished(LocalDate.of(2000, 6, 30));

        when(paperRepository.findById(anyLong()))
                .thenReturn(mockPaper);
        when(paperMapper.toEntity(any(Paper.class), any(PaperRequest.class)))
                .thenReturn(paper);
        when(paperRepository.save(any(Paper.class)))
                .thenReturn(paper);
        when(paperMapper.toDto(any(Paper.class)))
                .thenReturn(mockResponse);

        PaperRequest updateRequest = new PaperRequest();
        updateRequest.setTitle("title2");
        updateRequest.setAuthor("author2");
        updateRequest.setPublished(LocalDate.of(2001, 6, 30));

        // when
        PaperResponse actual = paperService.update(1L, updateRequest);

        // then
        assertThat(actual.getTitle()).isEqualTo(updateRequest.getTitle());
    }

    // test void method
    @Test
    void givenPaper_whenDelete_thenDbIsEmpty() {
        // given
        doNothing()
                .when(paperRepository).deleteById(anyLong());

        // when
        paperService.delete(1L);

        // then
        // verify the mocked object being called
        verify(paperRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(paperRepository);
    }

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
}