package co.com.ikitech.usecase.user.credit;

import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.credit.CreditOperations;
import co.com.ikitech.model.user.credit.Deposit;
import co.com.ikitech.model.user.error.CreditMessageError;
import co.com.ikitech.model.user.error.UserMessageError;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.repository.Repository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreditUseCase implements CreditOperations {

   private final Repository<Credit> repository;

    public Mono<Credit> getById(String id){

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new AppException(UserMessageError.USER_NOT_EXIST.value)));
    }

    public Mono<Credit> update(String id, Deposit deposit){

        return Mono.just(deposit).flatMap(credit1 -> this.getById(id))
                .flatMap(creditDB -> updateCreditModel(deposit, creditDB))
                .flatMap(repository::save);
    }

    public Mono<Void> deleteCredit(String id){

        return this.getById(id)
                .flatMap(credit -> {
                    if (credit.getRemainingDebt() == 0){
                        return repository.deleteById(id);
                    }else {
                        return Mono.error(new AppException(CreditMessageError.REMAIN_DEBIT_PENDING.value));
                    }
                    }
                );
    }
}
