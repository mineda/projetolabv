package br.gov.sp.fatec.projetolab5.service;

import java.util.List;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;

public interface AutorizacaoService {

    public Autorizacao buscarPeloNome(String nome);

    public List<Autorizacao> buscarTodas();

    public Autorizacao novaAutorizacao(String nome);
    
}
