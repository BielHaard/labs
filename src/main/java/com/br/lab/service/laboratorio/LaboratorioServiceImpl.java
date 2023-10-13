package com.br.lab.service.laboratorio;

import com.br.lab.dto.LaboratorioDTO;
import com.br.lab.entity.Laboratorio;
import com.br.lab.exception.ItemNotFoundException;
import com.br.lab.repository.LaboratorioRepository;
import com.br.lab.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaboratorioServiceImpl implements LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Laboratorio> getAllLaboratorios() {
        return laboratorioRepository.findAll();
    }

    @Override
    public Laboratorio getLaboratorioById(Long id) {
        Optional<Laboratorio> optionalLaboratorio = laboratorioRepository.findById(id);
        return optionalLaboratorio.orElse(null);
    }


    public List<LaboratorioDTO> obterInformacoesLaboratorios() {
        return laboratorioRepository.findAll().stream()
                .map(this::mapLaboratorioToDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    private LaboratorioDTO mapLaboratorioToDTO(Laboratorio laboratorio) {
        var laboratorioDTO = new LaboratorioDTO();
        laboratorioDTO.setCodigoLaboratorio(laboratorio.getId());
        laboratorioDTO.setNomeLaboratorio(laboratorio.getNome().toUpperCase());
        laboratorioDTO.setQuantidadePessoas(obterQuantidadePessoas(laboratorio));
        return laboratorioDTO;
    }

    private int obterQuantidadePessoas(Laboratorio laboratorio) {
        return Math.toIntExact(pessoaRepository.countByLaboratorio(laboratorio));
    }

    @Override
    public Laboratorio createLaboratorio(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    @Override
    public Laboratorio updateLaboratorio(Long id, Laboratorio laboratorio) {
        if (laboratorioRepository.existsById(id)) {
            laboratorio.setId(id);
            return laboratorioRepository.save(laboratorio);
        }
        throw new ItemNotFoundException(id);
    }

    @Override
    public void deleteLaboratorio(Long id) {
        laboratorioRepository.deleteById(id);
    }
}