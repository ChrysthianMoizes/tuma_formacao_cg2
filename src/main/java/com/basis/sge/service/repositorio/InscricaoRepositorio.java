package com.basis.sge.service.repositorio;

import com.basis.sge.service.dominio.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepositorio extends JpaRepository<Inscricao, Integer> {
}
