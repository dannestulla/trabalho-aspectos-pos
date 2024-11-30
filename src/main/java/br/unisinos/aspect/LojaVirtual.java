package br.unisinos.aspect;


import br.unisinos.aspect.exceptions.IdInvalidoException;
import br.unisinos.aspect.exceptions.PedidoNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class LojaVirtual {
    private List<Pedido> pedidos = new ArrayList<>();

    public void cadastrarPedido(Pedido pedido) {
        if (pedido == null)
            throw new IllegalArgumentException("O pedido nao pode ser nulo");
        pedidos.add(pedido);
    }
    public List<Pedido> listarPedidos() {
        return pedidos;
    }
    public void alterarPedido(int id, Pedido novoPedido) {
        if (id <= 0)
            throw new IdInvalidoException(id);

        boolean encontrado = false;
        for (Pedido p : pedidos) {
           if (p.getId() == id) {
                p.setCliente(novoPedido.getCliente());
                p.setProdutos(novoPedido.getProdutos());
                p.setValorTotal(novoPedido.getValorTotal());
                encontrado = true;
                break;
            }
        }
        // nao encontrado
        if (!encontrado) {
            throw new PedidoNaoEncontradoException(id);
        }
    }
}
