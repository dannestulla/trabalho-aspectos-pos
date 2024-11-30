package br.unisinos.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class AutenticacaoAspect {
    private static final Logger logger = LogManager.getLogger(AutenticacaoAspect.class);
    private static boolean autenticado = false; // Simulação de controle de autenticação

    // Método para autenticar o usuário (simulado)
    public static void autenticar() {
        logger.info("\n#### AUTENTICACAO #### - Usuario autenticado");
        autenticado = true;
    }

    // Método para desautenticar o usuário (simulado)
    public static void desautenticar() {
        logger.info("\n#### AUTENTICACAO #### - Usuario nao autenticado");
        autenticado = false;
    }

    @Before("execution(void LojaVirtual.cadastrarPedido(..)) || execution(void LojaVirtual.alterarPedido(..))")
    public void verificarAutenticacao(JoinPoint joinPoint) {
        if (!autenticado) {
            Signature assinatura = joinPoint.getSignature();
            String chamada = assinatura.toString();
            String argumentos = Arrays.toString(joinPoint.getArgs());
            logger.info("\n#### AUTENTICACAO #### - Usuario nao autenticado\nna chamada " + chamada + " com argumentos " + argumentos);
            throw new SecurityException("Usuário não autenticado. Acesso negado.");
        }
    }
}
