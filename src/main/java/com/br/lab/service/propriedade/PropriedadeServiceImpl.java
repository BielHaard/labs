package com.br.lab.service.propriedade;

import com.br.lab.entity.Propriedade;
import com.br.lab.exception.ItemNotFoundException;
import com.br.lab.repository.PropriedadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropriedadeServiceImpl implements PropriedadeService {

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    @Override
    public List<Propriedade> getAllPropriedades() {
        return propriedadeRepository.findAll();
    }

    @Override
    public Propriedade getPropriedadeById(Long id) {
        Optional<Propriedade> optionalPropriedade = propriedadeRepository.findById(id);
        return optionalPropriedade.orElse(null);
    }

    @Override
    public Propriedade createPropriedade(Propriedade propriedade) {
        return propriedadeRepository.save(propriedade);
    }

    @Override
    public Propriedade updatePropriedade(Long id, Propriedade propriedade) {
        if (propriedadeRepository.existsById(id)) {
            propriedade.setId(id);
            return propriedadeRepository.save(propriedade);
        }
        throw new ItemNotFoundException(id);
    }

    @Override
    public void deletePropriedade(Long id) {
        propriedadeRepository.deleteById(id);
    }
}