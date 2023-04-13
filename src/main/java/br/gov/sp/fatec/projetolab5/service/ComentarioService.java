package br.gov.sp.fatec.projetolab5.service;

import java.util.List;

import br.gov.sp.fatec.projetolab5.entity.Comentario;

public interface ComentarioService {

    public Comentario novoComentario(String texto, Long idAnotacao);

    public Comentario buscarPeloId(Long id);

    public List<Comentario> listarTodos();
    
}
