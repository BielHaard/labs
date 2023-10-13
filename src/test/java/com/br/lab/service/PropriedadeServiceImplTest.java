package com.br.lab.service;

import com.br.lab.entity.Propriedade;
import com.br.lab.exception.ItemNotFoundException;
import com.br.lab.repository.PropriedadeRepository;
import com.br.lab.service.propriedade.PropriedadeServiceImpl;
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

class PropriedadeServiceImplTest {

    @Mock
    private PropriedadeRepository propriedadeRepository;

    @InjectMocks
    private PropriedadeServiceImpl propriedadeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllPropriedades() {
        Propriedade propriedade1 = new Propriedade();
        propriedade1.setId(1L);

        Propriedade propriedade2 = new Propriedade();
        propriedade2.setId(2L);

        when(propriedadeRepository.findAll()).thenReturn(Arrays.asList(propriedade1, propriedade2));

        List<Propriedade> propriedades = propriedadeService.getAllPropriedades();

        assertEquals(2, propriedades.size());
    }

    @Test
    void getPropriedadeById() {
        Propriedade propriedade = new Propriedade();
        propriedade.setId(1L);

        when(propriedadeRepository.findById(1L)).thenReturn(Optional.of(propriedade));

        Propriedade result = propriedadeService.getPropriedadeById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void createPropriedade() {
        Propriedade propriedade = new Propriedade();
        propriedade.setId(1L);

        when(propriedadeRepository.save(any(Propriedade.class))).thenReturn(propriedade);

        Propriedade result = propriedadeService.createPropriedade(propriedade);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void updatePropriedade() {
        Propriedade propriedade = new Propriedade();
        propriedade.setId(1L);

        when(propriedadeRepository.existsById(1L)).thenReturn(true);
        when(propriedadeRepository.save(any(Propriedade.class))).thenReturn(propriedade);

        Propriedade result = propriedadeService.updatePropriedade(1L, propriedade);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void updatePropriedade_ItemNotFoundException() {
        when(propriedadeRepository.existsById(1L)).thenReturn(false);

        assertThrows(ItemNotFoundException.class, () -> propriedadeService.updatePropriedade(1L, new Propriedade()));
    }

    @Test
    void deletePropriedade() {
        assertDoesNotThrow(() -> propriedadeService.deletePropriedade(1L));

        verify(propriedadeRepository, times(1)).deleteById(1L);
    }
}
