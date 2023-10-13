package com.br.lab.service.propriedade;

import com.br.lab.entity.Propriedade;

import java.util.List;

public interface PropriedadeService {

    List<Propriedade> getAllPropriedades();

    Propriedade getPropriedadeById(Long id);

    Propriedade createPropriedade(Propriedade propriedade);

    Propriedade updatePropriedade(Long id, Propriedade propriedade);

    void deletePropriedade(Long id);
}
