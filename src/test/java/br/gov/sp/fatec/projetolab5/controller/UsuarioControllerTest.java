package br.gov.sp.fatec.projetolab5.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.service.SegurancaService;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SegurancaService service;

    @Test
    public void novoUsuarioTestOk() throws Exception {
        Usuario usuario = new Usuario("TesteMvc", "senha");
        usuario.setId(1L);
        Mockito.when(service.novoUsuario(any())).thenReturn(usuario);

        mvc.perform(post("/usuario")
            .content("{\"nome\":\"TesteMvc\", \"senha\":\"senha\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.nome").value("TesteMvc"))
            .andExpect(jsonPath("$.senha").value("senha"));
    }
    
}
