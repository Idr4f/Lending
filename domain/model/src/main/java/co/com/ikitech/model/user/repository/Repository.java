package co.com.ikitech.model.user.repository;

import reactor.core.publisher.*;

//Repositorio de genéricos para hacer más fácil reutilizar la clase
public interface Repository<T> {
    Mono<T> save(T t);
    Flux<T> findAll();
    Mono<T> findById(String id);
    Mono<Void> deleteById(String id);
    Mono<T> findByNames(String names);
}
