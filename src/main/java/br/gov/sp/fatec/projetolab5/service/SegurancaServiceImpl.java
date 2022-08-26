package br.gov.sp.fatec.projetolab5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.UsuarioRepository;

@Service
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired    
    private UsuarioRepository usuarioRepo;

    public SegurancaServiceImpl(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario novoUsuario(Usuario usuario) {
        if(usuario.getNome() == null || 
                usuario.getNome().isEmpty() ||
                usuario.getSenha() == null ||
                usuario.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Parâmetros inválidos!");
        }
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario novoUsuario(String nome, String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        return novoUsuario(usuario);
    }

    @Override
    public List<Usuario> todosUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepo.findById(id);
        if(optionalUsuario.isEmpty()) {
            throw new IllegalArgumentException("Id não existe!");
        }
        return optionalUsuario.get();

    }
    
}
