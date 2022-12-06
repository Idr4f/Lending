package co.com.ikitech.usecase.user;

import co.com.ikitech.model.user.error.UserMessageError;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.repository.*;
import co.com.ikitech.model.user.user.*;
import lombok.*;
import reactor.core.publisher.*;

@RequiredArgsConstructor
public class UserUseCase implements UserOperations {

    private final Repository<User> repository;

    public Mono<User> create(User user){

        return repository.save(user)
                .switchIfEmpty(Mono.error(new AppException(UserMessageError.USER_NOT_CREATE.value)));
    }

    public Flux<User> getAll(){

        return repository.findAll()
                .switchIfEmpty(Mono.error(new AppException(UserMessageError.NO_USERS_CREATED.value)));
    }

    public Mono<User> getById(String id){

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new AppException(UserMessageError.USER_NOT_EXIST.value)));
    }

    public Mono<User> update(String id,User user){

        return Mono.just(user).flatMap(u -> repository.findById(id))
                .switchIfEmpty(Mono.error(new AppException(UserMessageError.USER_NOT_EXIST.value)))
                .flatMap(userDB -> updateModel(user, userDB))
                .flatMap(repository::save);
    }

    public Mono<Void> deleteUser(String id) {
        //Es:
        return this.getById(id)
                .flatMap(r -> repository.deleteById(id));
    }
    /*Era:
    public Mono<Void> deleteUser(String id){

    return Mono.just(repository.findById(id)).switchIfEmpty(new AppException(UserMessageError.USER_NOT_EXIST.value)
            .flatMap(r -> repository.deleteById(id));
      }
     */
}
