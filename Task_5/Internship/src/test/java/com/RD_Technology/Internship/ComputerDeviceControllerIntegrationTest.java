package com.RD_Technology.Internship;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ComputerDeviceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ComputerDeviceRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void fullCrudFlow_shouldWork() throws Exception {
        ComputerDevice device = new ComputerDevice(101, "HP", 4.2, 65000L);

        mockMvc.perform(post("/api/computers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(device)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serialNo").value(101))
                .andExpect(jsonPath("$.brand").value("HP"));

        mockMvc.perform(get("/api/computers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].serialNo").value(101));

        mockMvc.perform(get("/api/computers/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("HP"));

        ComputerDevice updated = new ComputerDevice(101, "Dell", 4.8, 70000L);
        mockMvc.perform(put("/api/computers/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Dell"))
                .andExpect(jsonPath("$.rating").value(4.8));

        mockMvc.perform(delete("/api/computers/101"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/computers/101"))
                .andExpect(status().isNotFound());
    }
}

