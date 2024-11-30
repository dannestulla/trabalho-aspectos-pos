package br.unisinos.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AutenticacaoAspect {
    private static boolean autenticado = false; // Simulação de controle de autenticação
    private static final Logger logger = LogManager.getLogger(AutenticacaoAspect.class);


    @Before("execution(void LojaVirtual.cadastrarPedido(..)) || execution(void LojaVirtual.alterarPedido(..))")
    public void verificarAutenticacao() {
        if (!autenticado) {
            logger.info("#### AUTENTICACAO ####: Usuario nao autenticado");
            throw new SecurityException("Usuário não autenticado. Acesso negado.");
        }
    }

    // Método para autenticar o usuário (simulado)
    public static void autenticar() {
        logger.info("#### AUTENTICACAO ####: Usuario autenticado acessando recursos");
        autenticado = true;
    }

    // Método para desautenticar o usuário (simulado)
    public static void desautenticar() {
        logger.info("#### AUTENTICACAO ####: Usuario nao autenticado");
        autenticado = false;
    }
}
