package br.gov.sp.fatec.projetolab5.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import br.gov.sp.fatec.projetolab5.entity.Comentario;
import br.gov.sp.fatec.projetolab5.repository.AnotacaoRepository;
import br.gov.sp.fatec.projetolab5.repository.ComentarioRepository;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    @Autowired
    private ComentarioRepository comentarioRepo;

    @Autowired
    private AnotacaoRepository anotacaoRepo;

    public Comentario novoComentario(String texto, Long idAnotacao) {
        Optional<Anotacao> anotacaoOp = anotacaoRepo.findById(idAnotacao);
        if(anotacaoOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anotação inexistente!");
        }
        Comentario comentario = new Comentario();
        comentario.setTexto(texto);
        comentario.setDataHora(new Date());
        comentario.setAnotacao(anotacaoOp.get());
        return comentarioRepo.save(comentario);
    } 

    public Comentario buscarPeloId(Long id) {
        Optional<Comentario> comentarioOp = comentarioRepo.findById(id);
        if(comentarioOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário não encontrado!");
        }
        return comentarioOp.get();
    }

    public List<Comentario> listarTodos() {
        return comentarioRepo.findAll();
    }
    
}
