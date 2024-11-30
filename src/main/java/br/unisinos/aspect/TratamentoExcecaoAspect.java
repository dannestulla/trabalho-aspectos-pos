package br.unisinos.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class TratamentoExcecaoAspect {

    private static final Logger logger = LogManager.getLogger(TratamentoExcecaoAspect.class);

    @AfterThrowing(pointcut = "execution(* LojaVirtual.*(..))", throwing = "ex")
    public void tratarExcecao(JoinPoint joinPoint, Throwable ex) {
        // Reportar o erro
        Signature assinatura = joinPoint.getSignature();
        String nomeMetodo = assinatura.getName();
        String chamada = assinatura.toString();
        String argumentos = Arrays.toString(joinPoint.getArgs());
        logger.error("\n ### EXCECAO ### - Excecao no método: "
                + nomeMetodo + " com argumentos "
                + argumentos + "\ne a chamada: " + chamada + "\ne a excecao é: "
                + ex.getMessage());
    }
}

