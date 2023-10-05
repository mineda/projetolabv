package br.gov.sp.fatec.projetolab5.service;

import java.util.List;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;

public interface AnotacaoService {

    public Anotacao novaAnotacao(String texto, String usuario);

    public Anotacao buscarAnotacao(Long id);

    public List<Anotacao> buscarTodasAnotacoes();

    public Anotacao anotacaoComplementar(Long idAnotacao, String texto);
    
}
