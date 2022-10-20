package br.gov.sp.fatec.projetolab5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;
import br.gov.sp.fatec.projetolab5.repository.AutorizacaoRepository;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {

    @Autowired
    private AutorizacaoRepository repo;

    @Override
    public Autorizacao buscarPeloNome(String nome) {
        return repo.getByNome(nome);
    }

    @Override
    public List<Autorizacao> buscarTodas() {
        return repo.findAll();
    }

    @Override
    public Autorizacao novaAutorizacao(String nome) {
        if(nome == null ||
            nome.isEmpty()) {
            throw new IllegalArgumentException("Parâmetros inválidos!");
        }
        Autorizacao autorizacao = new Autorizacao();
        autorizacao.setNome(nome);
        try {
            autorizacao = repo.save(autorizacao);
        }
        catch(IllegalArgumentException exception) {
            throw new RuntimeException("Erro ao salvar autorização", exception);
        }
        return autorizacao;
    }
    
}
