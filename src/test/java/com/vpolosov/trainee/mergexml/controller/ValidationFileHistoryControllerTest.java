package com.vpolosov.trainee.mergexml.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpolosov.trainee.mergexml.dtos.ValidationFileHistoryDto;
import com.vpolosov.trainee.mergexml.repository.ValidationFileHistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(value = "file:src/test/resources/test_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
class ValidationFileHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ValidationFileHistoryRepository repository;

    @Test
    void testAllHistoryEndpointWorksCorrect() throws Exception {
        MockHttpServletResponse mockResponse = mockMvc
                .perform(get("/fileHistory/all"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        List<ValidationFileHistoryDto> response = objectMapper.readValue(
                mockResponse.getContentAsByteArray(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ValidationFileHistoryDto.class)
        );
        assertAll(
                () -> Assertions.assertEquals(9, response.size()),
                () -> assertTrue(
                        response.get(0).getValidationDate().isAfter(response.get(response.size() - 1).getValidationDate())
                )
        );
    }

    @Test
    void testWhatIfDBIsEmptyWHenReturnEmptyList() throws Exception {
        repository.deleteAll();
        MockHttpServletResponse mockResponse = mockMvc
                .perform(get("/fileHistory/all"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        List<ValidationFileHistoryDto> response = objectMapper.readValue(
                mockResponse.getContentAsByteArray(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ValidationFileHistoryDto.class)
        );
        assertTrue(response.isEmpty());
    }
}