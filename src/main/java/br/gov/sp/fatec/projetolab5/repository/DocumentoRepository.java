package br.gov.sp.fatec.projetolab5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.projetolab5.entity.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long>{

    public Optional<Documento> findByTipoAndNumero(String tipo, Long numero);
    
}
