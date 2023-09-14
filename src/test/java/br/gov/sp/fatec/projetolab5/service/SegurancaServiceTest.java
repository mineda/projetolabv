package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.UsuarioRepository;

@SpringBootTest
public class SegurancaServiceTest {

    @Autowired
    private SegurancaService service;

    @MockBean
    private UsuarioRepository repo;

    @Test
    public void novoUsuarioUsuarioNomeNull() {
        Usuario usuario = new Usuario();
        usuario.setSenha("1234");
        assertThrows(IllegalArgumentException.class, () -> {
            service.novoUsuario(usuario);
        });
    }

    @Test
    public void novoUsuarioUsuarioNomeVazio() {
        Usuario usuario = new Usuario();
        usuario.setNome("");
        usuario.setSenha("1234");
        assertThrows(IllegalArgumentException.class, () -> {
            service.novoUsuario(usuario);
        });
    }

    @Test
    public void novoUsuarioOk() {
        Usuario usuario = new Usuario();
        usuario.setNome("mineda");
        usuario.setSenha("1234");
        Mockito.when(repo.save(any())).thenReturn(usuario);
        assertDoesNotThrow(() -> {
            service.novoUsuario(usuario);
        });
    }
    
}
