package co.com.ikitech.model.user.customer;

import co.com.ikitech.model.user.credit.Credit;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//operaciones a realizar con el model, sin usar directamente el model
public interface CustomerOperations {

    default Mono<Customer> updateModel(Customer user, Customer userDB){

        return Mono.just(userDB
                .toBuilder()
                        .id(userDB.getId())
                        .names(user.getNames())
                        .surNames(user.getSurNames())
                        .documentType(user.getDocumentType())
                        .documentNumber(user.getDocumentNumber())
                        .address(user.getAddress())
                        .phone(user.getPhone())
                .build());
    }

}
