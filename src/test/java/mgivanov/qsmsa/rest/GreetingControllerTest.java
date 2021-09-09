package mgivanov.qsmsa.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import mgivanov.qsmsa.entity.Greeting;
import mgivanov.qsmsa.service.GreetingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class GreetingControllerTest {
// для отката изменений в бд до стартового набора данных пометим модифицирующие тесты @Transactional
    @Autowired
    private MockMvc mvc;

    @Autowired
    private GreetingService greetingService;

    @Test
    void getAllGreetings() throws Exception {
        List<Greeting> greetings = Arrays.asList(new Greeting(1L,"Hello, Max!"),new Greeting(2L,"Hello, Peter!"));
        greetingService.getAll();
        assertIterableEquals(greetingService.getAll(),greetings);

        mvc.perform(get("/api/greetings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].content", Matchers.is(greetings.get(0).getContent())))
                .andExpect(jsonPath("$[1].content", Matchers.is(greetings.get(1).getContent())));

    }


    @Test
    void getGreeting() throws Exception {
        Greeting greeting = new Greeting(1L,"Hello, Max!");
        assertEquals(greeting, greetingService.getById(1L));

        mvc.perform(get("/api/greetings/1"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.content", Matchers.is("Hello, Max!")))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void create() throws Exception{
        Greeting greeting = new Greeting("Hello,Peter Pan!");

        mvc.perform(post("/api/greetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(greeting)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", Matchers.is("Hello,Peter Pan!")))
                .andExpect(jsonPath("$.id").exists())

         ;

    }

    @Test
    @Transactional
    void update() throws Exception {
        mvc.perform(put("/api/greetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new Greeting(1, "Hello!"))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.content", Matchers.is("Hello!")));
    }

    @Test
    @Transactional
    void deleteGreeting() throws Exception {
        mvc.perform(delete("/api/greetings/1"))
                .andExpect(status().isOk());
    }
}