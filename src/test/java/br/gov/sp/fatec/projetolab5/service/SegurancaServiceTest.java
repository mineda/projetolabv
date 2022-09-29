package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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
    private UsuarioRepository usuarioRepo;

    @BeforeEach
    public void setUp() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setSenha("Senha");
        Optional<Usuario> usuarioOp = Optional.of(usuario);
        Mockito.when(usuarioRepo.findById(any())).thenReturn(usuarioOp);
        Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);
    }

    @Test
    public void buscarUsuarioPorIdTestOk() {
        assertEquals(service.buscarUsuarioPorId(1L).getNome(), "Teste");
    }

    @Test
    public void novoUsuarioTestNOkNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
                service.novoUsuario(null, "Senha");
            });
    }

    @Test
    public void novoUsuarioTestOk() {
        assertDoesNotThrow(() -> {
                service.novoUsuario("Teste", "Senha");
            });
    }
    
}
