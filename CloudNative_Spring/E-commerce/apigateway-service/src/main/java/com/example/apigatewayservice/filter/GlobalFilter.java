package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    public GlobalFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Custom PRE Filter
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Global Filter baseMessage : {}", config.getBaseMessage());

            if (config.isPreLogger()){
                log.info("Global Filter Start : Request Time -> {}", new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss").format(new Date()));
                log.info("Global Filter Start : Request id -> {}", request.getId());
            }

            // Custom POST Filter
            return chain.filter(exchange).then(Mono.fromRunnable(()-> {
                if(config.isPostLogger())
                    log.info("Global Filter End : Response status code-> {}",response.getStatusCode());
            }));
        };
    }

    @Data
    public static class Config{
        // Put the configuration ProPerties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
