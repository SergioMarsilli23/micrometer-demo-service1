package com.gentera.domain.adapter.out.gateway;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.gentera.domain.adapter.out.exception.CircuitBreakerException;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;

import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class InfoGateway {

    private final ReactiveCircuitBreaker reactiveCircuitBreaker;
    private final WebClient.Builder webClientBuilder;

    private String url = "http://localhost:8090/rest";

    public InfoGateway(ReactiveCircuitBreakerFactory circuitBreakerFactory, 
    		WebClient.Builder webClientBuilder) {
        this.reactiveCircuitBreaker = circuitBreakerFactory.create("myCircuitBreaker");
        this.webClientBuilder = webClientBuilder;
    }

	public Mono<String> call() {
        WebClient webClient = webClientBuilder
                .baseUrl(url).build();
        
        return reactiveCircuitBreaker.run(webClient.get()
                .uri("/info")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class),
                throwable -> {
                    return Mono.error(new CircuitBreakerException(
                            "El servicio no responde en este momento."));
                });
    }
}
