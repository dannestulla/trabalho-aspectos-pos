package br.unisinos.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AspectApplication {
    private static final Logger logger = LogManager.getLogger(AspectApplication.class);


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        LojaVirtual loja = context.getBean(LojaVirtual.class);

        /* Casos de sucesso:
         *  - Usuario esta logado
         *  - Os logs de auditoria e notificacao serao disparados
         * */
        logger.info("\n------------- CASOS DE SUCESSO ------------");
        AutenticacaoAspect.autenticar();
        Pedido pedido = new Pedido(
                1, "Cliente A",
                List.of("Produto 1", "Produto 2"), 100.0);
        loja.cadastrarPedido(pedido);
        loja.listarPedidos();
        pedido.setValorTotal(120.0);
        loja.alterarPedido(1, pedido);

        /* Tratamento de excecao */
        logger.info("\n------------- TRATAMENTO DE EXCESSAO ------------");
        try {
            loja.cadastrarPedido(null);
        } catch (RuntimeException e) {
        }
        try {
            Pedido pedidoInvalido = new Pedido(-1, "Cliente A", null, 100.0);
            loja.cadastrarPedido(pedidoInvalido);
        } catch (Exception e) {
        }
        try {
            Pedido pedidoInvalido = new Pedido(-1, "Cliente A", null, 100.0);
            loja.alterarPedido(-1, pedidoInvalido);
        } catch (Exception e) {
        }

        /* Sem autenticacao */
        logger.info("\n------------- NAO AUTENTICADO ------------");
        AutenticacaoAspect.desautenticar();
        try {
            loja.cadastrarPedido(pedido);
        } catch (RuntimeException e) {
        }


        context.close();
    }
}
