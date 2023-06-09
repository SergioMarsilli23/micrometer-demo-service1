package com.gentera.domain.handler;

import com.gentera.domain.adapter.out.gateway.InfoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@Slf4j
@RequiredArgsConstructor
public class InfoHandler implements Handler<Mono<String>> {
    
	private final InfoGateway infoGateway;
    
    @Override
    public Mono<String> handle() {
    	return infoGateway.call();
    }
}
