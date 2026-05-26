package com.bajajfinserv.bfhl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BfhlControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPostBfhlSuccess() throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("data", Arrays.asList("a", "1", "334", "4", "R", "$"));

        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success", is(true)))
                .andExpect(jsonPath("$.user_id", is("rajni_kumawat_17092003")))
                .andExpect(jsonPath("$.email", is("rajnikumawat230476@acropolis.in")))
                .andExpect(jsonPath("$.roll_number", is("0827CI231106")))
                .andExpect(jsonPath("$.odd_numbers", contains("1")))
                .andExpect(jsonPath("$.even_numbers", contains("334", "4")))
                .andExpect(jsonPath("$.alphabets", contains("A", "R")))
                .andExpect(jsonPath("$.special_characters", contains("$")))
                .andExpect(jsonPath("$.sum", is(339)))
                .andExpect(jsonPath("$.concat_string", is("Ra")));
    }

    @Test
    public void testGetBfhlSuccess() throws Exception {
        mockMvc.perform(get("/bfhl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation_code", is(1)));
    }

    @Test
    public void testPostBfhlInvalidPayloadFormat() throws Exception {
        // Send a malformed JSON to trigger exception handler
        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{invalid json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success", is(false)))
                .andExpect(jsonPath("$.error_message", containsString("Invalid JSON")));
    }
}
