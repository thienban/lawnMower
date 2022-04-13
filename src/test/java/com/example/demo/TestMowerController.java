package com.example.demo;

import com.example.demo.controller.MowerController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MowerController.class)
public class TestMowerController {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void instruction_test() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/coordinates")
                .content(new String(Files.readAllBytes(Paths.get("src/test/java/com/example/demo/json/instruction.json"))));

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .string("GET Response");

        this.mvc
                .perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(new String(Files.readAllBytes(Paths.get("src/test/java/com/example/demo/json/positions.json")))));
    }
}
