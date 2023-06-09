package com.gentera.domain.handler;


@FunctionalInterface
public interface Handler<R> {
    R handle();
}