package br.gov.sp.fatec.projetolab5.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class LancamentoControllerIntegrationTest {


    @Autowired
    private MockMvc mvc;   

    @Test
    public void buscarPeloIdTestOk() throws Exception {
        mvc.perform(get("/lancamento/{1}", 1f))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("1"));
    }
    
}
