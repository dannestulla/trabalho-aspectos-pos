package br.unisinos.aspect;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

@Configuration
public class AspectApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		LojaVirtual loja = context.getBean(LojaVirtual.class);

		/* Casos de sucesso */
		AutenticacaoAspect.autenticar();
		Pedido pedido = new Pedido(
				1, "Cliente A",
				List.of("Produto 1", "Produto 2"), 100.0);
		loja.cadastrarPedido(pedido);
		loja.listarPedidos();
		pedido.setValorTotal(120.0);
		loja.alterarPedido(1, pedido);

		/* Tratamento de excecao */
		try {
			loja.cadastrarPedido(null);
		} catch (RuntimeException e) {};
		try {
			pedido.setId(-1);
			loja.alterarPedido(1, pedido);
		} catch (Exception e) {}

		/* Sem autenticacao */
		AutenticacaoAspect.desautenticar();


		context.close();
	}
}
