package com.sda.testing.advanced.dto;

import com.sda.testing.advanced.model.Paper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class PaperMapperTest {

    // functional test
    // unit test a simple method
    // create test scenario
    @Test
    void givenEntity_whenToDto_thenReturnDto() {
        PaperMapper paperMapper = new PaperMapper();

        Paper paper = new Paper();
        paper.setId(1L);
        paper.setTitle("game of thrones");
        paper.setAuthor("george martin");
        paper.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse expected = new PaperResponse();
        expected.setId(1L);
        expected.setTitle("game of thrones");
        expected.setAuthor("george martin");
        expected.setPublished(LocalDate.of(2000, 6, 30));

        PaperResponse actual = paperMapper.toDto(paper);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void toEntity() {
    }
}