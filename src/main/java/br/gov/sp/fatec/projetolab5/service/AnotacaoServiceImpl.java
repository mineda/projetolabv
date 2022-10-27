package br.gov.sp.fatec.projetolab5.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.AnotacaoRepository;

@Service
public class AnotacaoServiceImpl implements AnotacaoService{

    @Autowired
    private AnotacaoRepository anotacaoRepo;

    @Autowired
    private SegurancaService service;

    @Override
    public Anotacao novaAnotacao(Anotacao anotacao) {
        if(anotacao.getTexto() == null ||
                anotacao.getTexto().isBlank() ||
                anotacao.getUsuario() == null ||
                anotacao.getUsuario().getId() == null) {
            throw new IllegalArgumentException("Anotação possui atributos em branco");
        }
        Usuario usuario;
        try {
            usuario = service.buscarUsuarioPorId(anotacao.getUsuario().getId());
        }
        catch(IllegalArgumentException exception) {
            throw new RuntimeException("Usuário não encontrado", exception);
        }
        if(anotacao.getDataHora() == null) {
            anotacao.setDataHora(new Date());
        }
        anotacao.setUsuario(usuario);
        return anotacaoRepo.save(anotacao);
    }

    @Override
    public List<Anotacao> buscaAnotacoes(String texto) {
        if(texto == null || texto.isBlank()) {
            return anotacaoRepo.findAll();
        }
        return anotacaoRepo.findByTextoContains(texto);
    }
    
}
