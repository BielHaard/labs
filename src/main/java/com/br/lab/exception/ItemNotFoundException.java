package com.br.lab.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Long id) {
        super("item não encontrada com o ID: " + id);
    }
}