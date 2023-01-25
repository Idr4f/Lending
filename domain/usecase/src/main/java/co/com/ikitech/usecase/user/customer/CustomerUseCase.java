package co.com.ikitech.usecase.user.customer;

import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.error.UserMessageError;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.repository.*;
import co.com.ikitech.model.user.customer.*;
import lombok.*;
import reactor.core.publisher.*;

@RequiredArgsConstructor
public class CustomerUseCase implements CustomerOperations {


    private final Repository<Customer> repository;

    public Mono<Customer> create(Customer user){

        return repository.save(user)
                .switchIfEmpty(Mono.error(new AppException(UserMessageError.USER_NOT_CREATE.value)));
    }

    public Flux<Customer> getAll(){

        return repository.findAll()
                .switchIfEmpty(Mono.error(new AppException(UserMessageError.NO_USERS_CREATED.value)));
    }

    public Mono<Customer> getById(String id){

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new AppException(UserMessageError.USER_NOT_EXIST.value)));
    }

    public Mono<Customer> update(String id, Customer user){

        return Mono.just(user).flatMap(user1 -> this.getById(id))
                .flatMap(userDB -> updateModel(user, userDB))
                .flatMap(repository::save);
    }

    public Mono<Void> deleteUser(String id) {
        //Es:
        return this.getById(id).flatMap(r -> repository.deleteById(id));
    }
    /*Era:
    public Mono<Void> deleteUser(String id){

    return Mono.just(repository.findById(id)).switchIfEmpty(new AppException(UserMessageError.USER_NOT_EXIST.value)
            .flatMap(r -> repository.deleteById(id));
      }
     */
    

}
