package com.br.lab.service.pessoa;

import com.br.lab.entity.Pessoa;

import java.util.List;

public interface PessoaService {

    List<Pessoa> getAllPessoas();

    Pessoa getPessoaById(Long id);

    Pessoa createPessoa(Pessoa pessoa);

    Pessoa updatePessoa(Long id, Pessoa pessoa);

    void deletePessoa(Long id);
}
