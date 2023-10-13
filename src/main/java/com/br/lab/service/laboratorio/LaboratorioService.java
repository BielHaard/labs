package com.br.lab.service.laboratorio;

import com.br.lab.dto.LaboratorioDTO;
import com.br.lab.entity.Laboratorio;

import java.util.List;

public interface LaboratorioService {

    List<Laboratorio> getAllLaboratorios();

    Laboratorio getLaboratorioById(Long id);

    Laboratorio createLaboratorio(Laboratorio laboratorio);

    Laboratorio updateLaboratorio(Long id, Laboratorio laboratorio);

    List<LaboratorioDTO> obterInformacoesLaboratorios();

    void deleteLaboratorio(Long id);
}
