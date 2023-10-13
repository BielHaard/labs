package com.br.lab.service;

import com.br.lab.entity.Pessoa;
import com.br.lab.exception.ItemNotFoundException;
import com.br.lab.repository.PessoaRepository;
import com.br.lab.service.pessoa.PessoaServiceImpl;
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

class PessoaServiceImplTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllPessoas() {
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1L);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(2L);

        when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoa1, pessoa2));
        List<Pessoa> pessoas = pessoaService.getAllPessoas();
        assertEquals(2, pessoas.size());
    }

    @Test
    void getPessoaById() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        Pessoa result = pessoaService.getPessoaById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void createPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        Pessoa result = pessoaService.createPessoa(pessoa);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void updatePessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);

        when(pessoaRepository.existsById(1L)).thenReturn(true);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        Pessoa result = pessoaService.updatePessoa(1L, pessoa);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void updatePessoa_ItemNotFoundException() {
        when(pessoaRepository.existsById(1L)).thenReturn(false);
        assertThrows(ItemNotFoundException.class, () -> pessoaService.updatePessoa(1L, new Pessoa()));
    }

    @Test
    void deletePessoa() {
        assertDoesNotThrow(() -> pessoaService.deletePessoa(1L));
        verify(pessoaRepository, times(1)).deleteById(1L);
    }
}
