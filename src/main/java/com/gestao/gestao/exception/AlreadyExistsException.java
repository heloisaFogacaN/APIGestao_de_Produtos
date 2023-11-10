package com.gestao.gestao.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(Long codigo) {
        super("Já há um produto com o código " + codigo + " existente.");
    }
}
