package com.br.lab.repository;

import com.br.lab.entity.Laboratorio;
import com.br.lab.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    long countByLaboratorio(Laboratorio laboratorio);
}
