package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.AnotacaoRepository;
import br.gov.sp.fatec.projetolab5.repository.UsuarioRepository;

@SpringBootTest
public class AnotacaoServiceTest {
    
    @Autowired
    private AnotacaoService service;

    @MockBean
    private UsuarioRepository usuarioRepo;

    @MockBean
    private AnotacaoRepository anotacaoRepo;

    @Test
    public void novaAnotacaoTestOk() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setSenha("Senha");
        Optional<Usuario> usuarioOp = Optional.of(usuario);
        Mockito.when(usuarioRepo.findByNome("Teste")).thenReturn(usuarioOp);

        Anotacao anotacao = new Anotacao("Texto bem longo!", LocalDateTime.now(), usuario);
        anotacao.setId(1L);

        Mockito.when(anotacaoRepo.save(any())).thenReturn(anotacao);
        
        assertEquals(1L, service.novaAnotacao("Texto bem longo!", "Teste").getId());
    }

    @Test
    public void novaAnotacaoTestNOkTextoNull() {
        assertThrows(ResponseStatusException.class, () -> {
            service.novaAnotacao(null, "Teste");
        });
    }

    @Test
    public void novaAnotacaoTestNOkTextoBranco() {
        assertThrows(ResponseStatusException.class, () -> {
            service.novaAnotacao("    ", "Teste");
        });
    }

    @Test
    public void novaAnotacaoTestNOkUsuarioNull() {
        assertThrows(ResponseStatusException.class, () -> {
            service.novaAnotacao("Texto muito longo!", null);
        });
    }

    @Test
    public void novaAnotacaoTestNOkTextoCurto() {
        assertThrows(ResponseStatusException.class, () -> {
            service.novaAnotacao("Olá", "Teste");
        });
    }

    @Test
    public void novaAnotacaoTestNOkUsuarioNaoEncontrado() {
        Mockito.when(usuarioRepo.findByNome("Admin")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            service.novaAnotacao("Texto muito longo!", "Admin");
        });
    }



}
