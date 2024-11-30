package br.unisinos.aspect.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(int id) {
        super("Pedido " + id + " nao encontrado");
    }
}
