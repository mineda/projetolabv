package br.gov.sp.fatec.projetolab5.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.gov.sp.fatec.projetolab5.entity.Usuario;

@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Test
    public void novoUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setNome("UsuarioTeste");
        usuario.setSenha("123");
        usuario = usuarioRepo.save(usuario);
        assertNotNull(usuario.getId());
    }

    @Test
    public void novoUsuarioUniqueNokTest() {
        Usuario usuario = new Usuario();
        usuario.setNome("admin");
        usuario.setSenha("123");
        assertThrows(DataIntegrityViolationException.class, () -> {
            usuarioRepo.save(usuario);
        });
    }
    
}
