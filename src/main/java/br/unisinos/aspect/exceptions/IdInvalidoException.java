package br.unisinos.aspect.exceptions;

public class IdInvalidoException extends IllegalArgumentException {
    public IdInvalidoException(int id) {
        super("O Id " + id + "é inválido");
    }
}
