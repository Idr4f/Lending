package co.com.ikitech.model.user.repository;

import reactor.core.publisher.*;

public interface Repository<T> {

    Mono<T> save(T t);
    Flux<T> findAll();

    Mono<T> findById(String id);
    Mono<Void> deleteById(String id);

}
