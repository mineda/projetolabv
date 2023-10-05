package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import br.gov.sp.fatec.projetolab5.entity.Comentario;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.AnotacaoRepository;
import br.gov.sp.fatec.projetolab5.repository.ComentarioRepository;

@SpringBootTest
public class ComentarioServiceTest {

    @Autowired
    private ComentarioService service;

    @MockBean
    private AnotacaoRepository anotacaoRepo;

    @MockBean
    private ComentarioRepository comentarioRepo;

    @Test
    public void novoComentarioTestOk() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setSenha("abc123");
        Anotacao anotacao = new Anotacao();
        anotacao.setId(1L);
        anotacao.setTexto("Anotação teste");
        anotacao.setUsuario(usuario);
        anotacao.setDataHora(LocalDateTime.now());
        Comentario comentario = new Comentario();
        comentario.setId(1L);
        comentario.setTexto("Comentário teste");
        comentario.setDataHora(new Date());
        comentario.setAnotacao(anotacao);
        Mockito.when(anotacaoRepo.findById(1L)).thenReturn(Optional.of(anotacao));
        Mockito.when(comentarioRepo.save(any())).thenReturn(comentario);
        assertEquals("Comentário teste", service.novoComentario("Comentário teste", 1L).getTexto());
    }

    @Test
    public void novoComentarioAutorizacaoNaoExisteTestNOk() {
        Mockito.when(anotacaoRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {
            service.novoComentario("Comentário teste", 1L);
        });
    }


    
}
