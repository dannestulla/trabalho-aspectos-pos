package br.unisinos.aspect;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.apache.logging.log4j.Logger;

@Aspect
public class LojaVirtualAspect {

    private static final Logger logger = LogManager.getLogger(LojaVirtualAspect.class);

    // Log antes de cadastrar o pedido
    @Before("execution(void LojaVirtual.cadastrarPedido(..)) && args(pedido)")
    public void logCadastro(Pedido pedido) {
        logger.info("### AUDITORIA #### - Cadastro de pedido realizado: " + pedido);
    }

    // Log antes de listar os pedidos
    @Before("execution(java.util.List<Pedido> LojaVirtual.listarPedidos())")
    public void logListagem() {
        logger.info("### AUDITORIA #### - Listagem de pedidos realizada.");
    }

    // Log antes de alterar um pedido
    @Before("execution(void LojaVirtual.alterarPedido(..)) && args(id, novoPedido)")
    public void logAlteracao(int id, Pedido novoPedido) {
        logger.info("### AUDITORIA #### - Alteração do pedido de ID " + id + " para: " + novoPedido);
    }
}