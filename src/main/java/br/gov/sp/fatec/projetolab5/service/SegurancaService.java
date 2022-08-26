package br.gov.sp.fatec.projetolab5.service;

import java.util.List;

import br.gov.sp.fatec.projetolab5.entity.Usuario;

public interface SegurancaService {
    
    public Usuario novoUsuario(Usuario usuario);

    public Usuario novoUsuario(String nome, String senha);

    public List<Usuario> todosUsuarios();

    public Usuario buscarUsuarioPorId(Long id);
}
