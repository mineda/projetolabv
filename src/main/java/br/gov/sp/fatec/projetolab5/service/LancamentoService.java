package br.gov.sp.fatec.projetolab5.service;

import java.util.List;

import br.gov.sp.fatec.projetolab5.controller.LancamentoDTO;
import br.gov.sp.fatec.projetolab5.entity.Lancamento;

public interface LancamentoService {

    public Lancamento novoLancamento(LancamentoDTO lancamentoDTO);

    public Integer contarLancamentosComDuracaoSuperior(Float duracao);

    public List<Lancamento> buscarTodos();
    
}
