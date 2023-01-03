package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    public LoggingFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Custom PRE Filter
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            log.info("Logging Filter baseMessage : {}", config.getBaseMessage());
//
//            if (config.isPreLogger()){
//                log.info("Logging Filter Start : Request Time -> {}", new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss").format(new Date()));
//                log.info("Logging Filter Start : Request id -> {}", request.getId());
//            }
//
//            // Custom POST Filter
//            return chain.filter(exchange).then(Mono.fromRunnable(()-> {
//                if(config.isPostLogger())
//                    log.info("Logging Filter End : Response status code-> {}",response.getStatusCode());
//            }));
//        };
        GatewayFilter filter = new OrderedGatewayFilter((exchange,chain) ->{
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging Filter baseMessage : {}", config.getBaseMessage());

            if (config.isPreLogger()){
                log.info("Logging PRE Filter  : Request Time -> {}", new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss").format(new Date()));
                log.info("Logging PRE Filter Start : Request id -> {}", request.getId());
            }

            // Custom POST Filter
            return chain.filter(exchange).then(Mono.fromRunnable(()-> {
                if (config.isPostLogger())
                    log.info("Logging POST Filter : Response status code-> {}", response.getStatusCode());
            }));
        }, Ordered.LOWEST_PRECEDENCE);
        return filter;
    }

    @Data
    public static class Config{
        // Put the configuration ProPerties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
