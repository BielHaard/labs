package com.br.lab.controller;

import com.br.lab.entity.Propriedade;
import com.br.lab.service.propriedade.PropriedadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController {

    @Autowired
    private PropriedadeService propriedadeService;

    @GetMapping
    public ResponseEntity<List<Propriedade>> getAllPropriedades() {
        List<Propriedade> propriedades = propriedadeService.getAllPropriedades();
        return ResponseEntity.ok(propriedades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPropriedadeById(@PathVariable Long id) {
        Optional<Propriedade> optionalPropriedade = Optional.ofNullable(propriedadeService.getPropriedadeById(id));
        if (optionalPropriedade.isPresent()) {
            Propriedade propriedade = optionalPropriedade.get();
            return new ResponseEntity<>(propriedade, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Propriedade não existente, ID" + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPropriedade(@RequestBody Propriedade propriedade) {
        try {
            Propriedade createdPropriedade = propriedadeService.createPropriedade(propriedade);
            return ResponseEntity.ok(createdPropriedade);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CNPJ já existe.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePropriedade(@PathVariable Long id, @RequestBody Propriedade propriedade) {
        try{
            Propriedade updatedPropriedade = propriedadeService.updatePropriedade(id, propriedade);
            return ResponseEntity.ok(updatedPropriedade);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CNPJ já existe.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropriedade(@PathVariable Long id) {
        try {
            propriedadeService.deletePropriedade(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}