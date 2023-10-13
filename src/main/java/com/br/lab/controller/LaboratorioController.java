package com.br.lab.controller;

import com.br.lab.dto.LaboratorioDTO;
import com.br.lab.entity.Laboratorio;
import com.br.lab.service.laboratorio.LaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @GetMapping
    public ResponseEntity<List<Laboratorio>> getAllLaboratorios() {
        List<Laboratorio> laboratorios = laboratorioService.getAllLaboratorios();
        return ResponseEntity.ok(laboratorios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLaboratorioById(@PathVariable Long id) {
        Optional<Laboratorio> optionalLaboratorio = Optional.ofNullable(laboratorioService.getLaboratorioById(id));
        if (optionalLaboratorio.isPresent()) {
            Laboratorio laboratorio = optionalLaboratorio.get();
            return new ResponseEntity<>(laboratorio, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Laboratorio não existente, ID" + id);
        }
    }

    @GetMapping("/informacoes")
    public List<LaboratorioDTO> obterInformacoesLaboratorios() {
        return laboratorioService.obterInformacoesLaboratorios();
    }

    @PostMapping
    public ResponseEntity<?> createLaboratorio(@RequestBody Laboratorio laboratorio) {
        try {
            Laboratorio createdLaboratorio = laboratorioService.createLaboratorio(laboratorio);
            return ResponseEntity.ok(createdLaboratorio);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLaboratorio(@PathVariable Long id, @RequestBody Laboratorio laboratorio) {
        try{
            Laboratorio updatedLaboratorio = laboratorioService.updateLaboratorio(id, laboratorio);
            return ResponseEntity.ok(updatedLaboratorio);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratorio(@PathVariable Long id) {
        try {
            laboratorioService.deleteLaboratorio(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}