package br.gov.sp.fatec.projetolab5.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ComentarioControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;   

    @Test
    public void buscarPeloIdTestOk() throws Exception {
        mvc.perform(get("/comentario/id/{1}", 1L)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.texto").value("Essa anotação me ajudou muito na prova"));
    }

    @Test
    public void buscarPeloIdTestNOk() throws Exception {
        mvc.perform(get("/comentario/id/{1}", 2L)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
    }
    
}
