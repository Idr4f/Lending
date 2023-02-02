package co.com.ikitech.usecase.user.credit;

import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.model.user.credit.*;
import co.com.ikitech.model.user.error.CreditMessageError;
import co.com.ikitech.model.user.error.UserMessageError;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.repository.*;
import lombok.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreditUseCase implements CreditOperations{

   private final Repository<Credit> repository;
    private final Repository<Account> repo;

    public Mono<Credit> getById(String id){

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new AppException(UserMessageError.USER_NOT_EXIST.value)));
    }

    public Mono<Credit> update(String id,Credit credit){

        return Mono.just(credit).flatMap(credit1 -> this.getById(id))
                .flatMap(creditDB -> updateCreditModel(credit, creditDB))
                .flatMap(repository::save);
    }

    public Mono<Void> deleteCredit(String id){

        return this.getById(id).flatMap(r -> repository.deleteById(id));
    }
}
