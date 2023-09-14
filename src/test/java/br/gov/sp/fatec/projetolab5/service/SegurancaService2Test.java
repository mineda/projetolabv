package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
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
public class SegurancaService2Test {

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
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(usuario);
        Optional<Usuario> usuarioOp = Optional.of(usuario);
        Mockito.when(usuarioRepo.findById(1L)).thenReturn(usuarioOp);
        Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);
        Mockito.when(usuarioRepo.findAll()).thenReturn(usuarios);
    }

    @Test
    public void buscarUsuarioPorIdTestOk() {
        assertEquals("Teste", service.buscarUsuarioPorId(1L).getNome());
    }

    @Test
    public void buscarUsuarioPorIdTestNOk() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.buscarUsuarioPorId(2L);
        });
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

    @Test
    public void todosUsuariosTestOk() {
        assertEquals(1, service.todosUsuarios().size());
    }
    
}
