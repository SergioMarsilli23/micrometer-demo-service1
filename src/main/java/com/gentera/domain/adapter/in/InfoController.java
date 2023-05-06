package com.gentera.domain.adapter.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gentera.domain.handler.InfoHandler;
import io.micrometer.observation.annotation.Observed;
import io.micrometer.tracing.annotation.ContinueSpan;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/rest/")
@RequiredArgsConstructor
public class InfoController {
    
	private final InfoHandler findUserHandler;

	@Observed(name = "service1", contextualName = "service1")
	@ContinueSpan(log = "info-controller")
    @GetMapping(value = "/info", produces = "application/json")
    public Mono<String> getById() {
        return findUserHandler.handle();
    }
    
}

