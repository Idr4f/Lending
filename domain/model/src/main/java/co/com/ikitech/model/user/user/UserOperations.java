package co.com.ikitech.model.user.user;

import reactor.core.publisher.*;

public interface UserOperations {

    default Mono<User> updateModel(User user, User userDB){

        return Mono.just(userDB
                .toBuilder()
                        .id(userDB.getId())
                        .names(user.getNames())
                        .surNames(user.getSurNames())
                        .documentType(user.getDocumentType())
                        .documentNumber(user.getDocumentNumber())
                        .email(user.getEmail())
                        .address(user.getAddress())
                        .phone(user.getPhone())
                .build());
    }
}
