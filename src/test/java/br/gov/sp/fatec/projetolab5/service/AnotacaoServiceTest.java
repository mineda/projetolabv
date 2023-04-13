package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.AnotacaoRepository;

@SpringBootTest
public class AnotacaoServiceTest {

    @Autowired
    private AnotacaoService service;

    @MockBean
    private AnotacaoRepository anotacaoRepo;

    @MockBean
    private SegurancaService segurancaService;

    @Test
    public void novaAnotacaoTestOk() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setSenha("abc123");
        Anotacao anotacao = new Anotacao();
        anotacao.setId(1L);
        anotacao.setTexto("Anotação teste");
        anotacao.setUsuario(usuario);
        anotacao.setDataHora(new Date());
        Mockito.when(segurancaService.buscarUsuarioPorId(1L)).thenReturn(usuario);
        Mockito.when(anotacaoRepo.save(any())).thenReturn(anotacao);
        assertEquals("Anotação teste", service.novaAnotacao(anotacao).getTexto());
    }

    @Test
    public void novaAnotacaoDataHoraNullTestOk() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setSenha("abc123");
        Anotacao anotacao = new Anotacao();
        anotacao.setId(1L);
        anotacao.setTexto("Anotação teste");
        anotacao.setUsuario(usuario);
        Mockito.when(segurancaService.buscarUsuarioPorId(1L)).thenReturn(usuario);
        Mockito.when(anotacaoRepo.save(any())).thenReturn(anotacao);
        assertEquals("Anotação teste", service.novaAnotacao(anotacao).getTexto());
    }

    @Test
    public void novaAnotacaoTextoNullTestNOk() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Anotacao anotacao = new Anotacao();
        anotacao.setId(1L);
        anotacao.setUsuario(usuario);
        anotacao.setDataHora(new Date());
        assertThrows(IllegalArgumentException.class, () -> {
            service.novaAnotacao(anotacao);
        });
    }

    @Test
    public void novaAnotacaoTextoBlankTestNOk() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Anotacao anotacao = new Anotacao();
        anotacao.setId(1L);
        anotacao.setTexto(" ");
        anotacao.setUsuario(usuario);
        anotacao.setDataHora(new Date());
        assertThrows(IllegalArgumentException.class, () -> {
            service.novaAnotacao(anotacao);
        });
    }

    @Test
    public void novaAnotacaoUsuarioNullTestNOk() {
        Anotacao anotacao = new Anotacao();
        anotacao.setId(1L);
        anotacao.setTexto("Anotação teste");
        anotacao.setDataHora(new Date());
        assertThrows(IllegalArgumentException.class, () -> {
            service.novaAnotacao(anotacao);
        });
    }

    @Test
    public void novaAnotacaoUsuarioIdNullTestNOk() {
        Usuario usuario = new Usuario();
        Anotacao anotacao = new Anotacao();
        anotacao.setId(1L);
        anotacao.setTexto("Anotação teste");
        anotacao.setUsuario(usuario);
        anotacao.setDataHora(new Date());
        assertThrows(IllegalArgumentException.class, () -> {
            service.novaAnotacao(anotacao);
        });
    }

    @Test
    public void novaAnotacaoUsuarioNaoExisteTestNOk() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setSenha("abc123");
        Anotacao anotacao = new Anotacao();
        anotacao.setId(1L);
        anotacao.setTexto("Anotação teste");
        anotacao.setUsuario(usuario);
        anotacao.setDataHora(new Date());
        Mockito.when(segurancaService.buscarUsuarioPorId(1L)).thenThrow(IllegalArgumentException.class);
        assertThrows(RuntimeException.class, () -> {
            service.novaAnotacao(anotacao);
        });
    }


}
