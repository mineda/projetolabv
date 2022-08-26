package br.gov.sp.fatec.projetolab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.projetolab5.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
