package br.gov.sp.fatec.projetolab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.projetolab5.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

    public Integer countByDuracaoGreaterThan(Float duracao);
    
}
