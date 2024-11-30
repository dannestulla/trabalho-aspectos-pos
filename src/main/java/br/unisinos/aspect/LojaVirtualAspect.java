package br.unisinos.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LojaVirtualAspect {

    private static final Logger logger = LogManager.getLogger(LojaVirtualAspect.class);

    // Log antes de cadastrar o pedido
    @Before("execution(void LojaVirtual.cadastrarPedido(..)) && args(pedido)")
    public void logCadastro(Pedido pedido) {
        logger.info("\n ### AUDITORIA #### - Cadastro de pedido realizado: " + pedido);
    }

    // Log antes de listar os pedidos
    @Before("execution(java.util.List<Pedido> LojaVirtual.listarPedidos())")
    public void logListagem() {
        logger.info("\n ### AUDITORIA #### - Listagem de pedidos realizada.");
    }

    // Log antes de alterar um pedido
    @Before("execution(void LojaVirtual.alterarPedido(..)) && args(id, novoPedido)")
    public void logAlteracao(int id, Pedido novoPedido) {
        logger.info("\n ### AUDITORIA #### - Alteração do pedido de ID " + id + " para: " + novoPedido);
    }
}