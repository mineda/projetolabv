package br.gov.sp.fatec.projetolab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {

    public Autorizacao getByNome(String nome);
    
}
