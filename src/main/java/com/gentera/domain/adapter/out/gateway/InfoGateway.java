package com.gentera.domain.adapter.out.gateway;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class InfoGateway {

    private final WebClient.Builder webClientBuilder;

    private String url = "http://localhost:8090/rest";

    public InfoGateway(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

	public Mono<String> call() {
        WebClient webClient = webClientBuilder
                .baseUrl(url).build();
        
        return webClient.get()
                .uri("/info")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
    }
}
