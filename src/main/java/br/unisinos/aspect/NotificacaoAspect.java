package br.unisinos.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class NotificacaoAspect {

    // Notificação antes do cadastro de um pedido
    @Before("execution(void LojaVirtual.cadastrarPedido(..)) && args(pedido)")
    public void notificarCadastro(Pedido pedido) {
        System.out.println("\n #### NOTIFICACAO #### - Pedido cadastrado: " + pedido);
    }

    // Notificação após a alteração de um pedido
    @After(value = "execution(void LojaVirtual.alterarPedido(..)) && args(id, novoPedido)", argNames = "id,novoPedido")
    public void notificarAlteracao(int id, Pedido novoPedido) {
        System.out.println("\n #### NOTIFICACAO #### - Pedido de ID " + id + " alterado para: " + novoPedido);
    }

    // Notificação após a listagem dos pedidos
    @After(value = "execution(void LojaVirtual.listarPedidos())")
    public void notificarListagem() {
        System.out.println("\n #### NOTIFICACAO #### - Listando todos os pedidos");
    }


}
