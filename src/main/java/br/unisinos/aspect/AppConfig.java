package br.unisinos.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public LojaVirtual lojaVirtual() {
        return new LojaVirtual();
    }

    @Bean
    public LojaVirtualAspect lojaVirtualAspect() {
        return new LojaVirtualAspect();
    }


    @Bean
    public NotificacaoAspect notificacaoAspect() {
        return new NotificacaoAspect();
    }

    @Bean
    AutenticacaoAspect autenticacaoAspect() {
        return new AutenticacaoAspect();
    }

    @Bean
    public TratamentoExcecaoAspect tratamentoExcecaoAspect() {
        return new TratamentoExcecaoAspect();
    }
}

