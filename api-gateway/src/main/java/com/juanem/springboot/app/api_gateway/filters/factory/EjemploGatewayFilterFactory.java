package com.juanem.springboot.app.api_gateway.filters.factory;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configuration>{

    public EjemploGatewayFilterFactory() {super(Configuration.class);}
    @Override
    public GatewayFilter apply(Configuration config) {
        //Se puede quitar el objeto orden y solo devolver la lambda
        return new OrderedGatewayFilter(lambda(config), 2);
    }

    private static GatewayFilter lambda(Configuration config) {
        return (exchange, chain) -> {
            log.info("Ejecutando pre gateway filter factory: " + config.cookieMsg);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {

                Optional.ofNullable(config.cookieValue).ifPresent(cookie -> {
                    exchange.getResponse().addCookie(ResponseCookie.from(config.cookieName, cookie).build());
                });
                log.info("Ejecutando post gateway filter factory: " + config.cookieMsg);
            }));
        };
    }

    @Override
    public List<String> shortcutFieldOrder(){
        return Arrays.asList("cookieMsg", "cookieValue", "cookieName");
    }
    @Getter
    @Setter
    public static class Configuration {
        private String cookieMsg;
        private String cookieValue;
        private String cookieName;
    }
}
