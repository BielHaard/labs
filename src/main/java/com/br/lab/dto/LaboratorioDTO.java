package com.br.lab.dto;

import com.br.lab.entity.Pessoa;

import java.util.List;

public class LaboratorioDTO {
    private Long codigoLaboratorio;
    private String nomeLaboratorio;
    private List<Pessoa> pessoas;

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    private int quantidadePessoas;

    public Long getCodigoLaboratorio() {
        return codigoLaboratorio;
    }

    public void setCodigoLaboratorio(Long codigoLaboratorio) {
        this.codigoLaboratorio = codigoLaboratorio;
    }

    public String getNomeLaboratorio() {
        return nomeLaboratorio;
    }

    public void setNomeLaboratorio(String nomeLaboratorio) {
        this.nomeLaboratorio = nomeLaboratorio;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

}
