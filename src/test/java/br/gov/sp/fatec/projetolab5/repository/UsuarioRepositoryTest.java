package br.gov.sp.fatec.projetolab5.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.projetolab5.entity.Usuario;

@SpringBootTest
@Transactional
@Rollback
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
    
}
