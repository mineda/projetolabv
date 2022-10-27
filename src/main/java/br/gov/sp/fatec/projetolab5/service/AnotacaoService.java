package br.gov.sp.fatec.projetolab5.service;

import java.util.List;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;

public interface AnotacaoService {

    public Anotacao novaAnotacao(Anotacao anotacao);

    public List<Anotacao> buscaAnotacoes(String texto);
    
}
