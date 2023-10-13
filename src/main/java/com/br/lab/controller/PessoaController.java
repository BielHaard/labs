package com.br.lab.controller;

import com.br.lab.entity.Pessoa;
import com.br.lab.service.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> pessoas = pessoaService.getAllPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPessoaById(@PathVariable Long id) {
        Optional<Pessoa> optionalPessoa = Optional.ofNullable(pessoaService.getPessoaById(id));
        if (optionalPessoa.isPresent()) {
            Pessoa pessoa = optionalPessoa.get();
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pessoa não existente, ID" + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa createdPessoa = pessoaService.createPessoa(pessoa);
            return ResponseEntity.ok(createdPessoa);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já existe.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        try{
            Pessoa updatedPessoa = pessoaService.updatePessoa(id, pessoa);
            return ResponseEntity.ok(updatedPessoa);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já existe.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        try {
            pessoaService.deletePessoa(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}