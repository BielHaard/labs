package com.br.lab.service;

import com.br.lab.entity.Laboratorio;
import com.br.lab.exception.ItemNotFoundException;
import com.br.lab.repository.LaboratorioRepository;
import com.br.lab.service.laboratorio.LaboratorioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaboratorioServiceTest {

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @InjectMocks
    private LaboratorioServiceImpl laboratorioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllLaboratorios() {
        Laboratorio laboratorio1 = new Laboratorio();
        laboratorio1.setId(1L);

        Laboratorio laboratorio2 = new Laboratorio();
        laboratorio2.setId(2L);

        when(laboratorioRepository.findAll()).thenReturn(Arrays.asList(laboratorio1, laboratorio2));

        List<Laboratorio> laboratorios = laboratorioService.getAllLaboratorios();

        assertEquals(2, laboratorios.size());
    }

    @Test
    void getLaboratorioById() {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(1L);

        when(laboratorioRepository.findById(1L)).thenReturn(Optional.of(laboratorio));

        Laboratorio result = laboratorioService.getLaboratorioById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void obterInformacoesLaboratorios() {
    }

    @Test
    void createLaboratorio() {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(1L);

        when(laboratorioRepository.save(any(Laboratorio.class))).thenReturn(laboratorio);

        Laboratorio result = laboratorioService.createLaboratorio(laboratorio);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void updateLaboratorio() {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setId(1L);

        when(laboratorioRepository.existsById(1L)).thenReturn(true);
        when(laboratorioRepository.save(any(Laboratorio.class))).thenReturn(laboratorio);

        Laboratorio result = laboratorioService.updateLaboratorio(1L, laboratorio);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void updateLaboratorio_ItemNotFoundException() {
        when(laboratorioRepository.existsById(1L)).thenReturn(false);

        assertThrows(ItemNotFoundException.class, () -> laboratorioService.updateLaboratorio(1L, new Laboratorio()));
    }

    @Test
    void deleteLaboratorio() {
        assertDoesNotThrow(() -> laboratorioService.deleteLaboratorio(1L));

        verify(laboratorioRepository, times(1)).deleteById(1L);
    }
}
