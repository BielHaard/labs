package com.br.lab.service.pessoa;

import com.br.lab.entity.Pessoa;
import com.br.lab.exception.ItemNotFoundException;
import com.br.lab.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa getPessoaById(Long id) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        return optionalPessoa.orElse(null);
    }

    @Override
    public Pessoa createPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        if (pessoaRepository.existsById(id)) {
            pessoa.setId(id);
            return pessoaRepository.save(pessoa);
        }
        throw new ItemNotFoundException(id);
    }

    @Override
    public void deletePessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}