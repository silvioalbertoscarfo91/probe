package com.kata.probe.movement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProbeControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnPositionAndDirectionAfterCommands() throws Exception {
        String commandsJson = "[\"FORWARD\", \"RIGHT\", \"FORWARD\"]";
        mockMvc.perform(post("/probe/movement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commandsJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.position.x").value(1))
            .andExpect(jsonPath("$.position.y").value(1))
            .andExpect(jsonPath("$.direction").value("E"));
    }

    @Test
    void shouldThrowExceptionForInvalidCommandEnum() throws Exception {
        //given a list with an invalid command value
        String invalidCommandsJson = "[\"FORWARD\", \"UP\", \"RIGHT\"]"; // "UP" is not a valid

        //when/then expect a 400 Bad Request due to deserialization failure
        mockMvc.perform(post("/probe/movement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidCommandsJson))
                .andExpect(status().isBadRequest()); //no globalExceptionHandler is defined
    }

    @Test
    void shouldThrowExceptionForNonListInput() throws Exception {
        //given a single object instead of a list
        String nonListJson = "{\"command\": \"FORWARD\"}"; // Not a list

        //when/then expect a 400 Bad Request due to type mismatch
        mockMvc.perform(post("/probe/movement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nonListJson))
                .andExpect(status().isBadRequest());
    }
}