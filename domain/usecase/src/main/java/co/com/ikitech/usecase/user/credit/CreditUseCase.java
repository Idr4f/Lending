package co.com.ikitech.usecase.user.credit;

import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.credit.CreditOperations;
import co.com.ikitech.model.user.credit.Deposit;
import co.com.ikitech.model.user.error.CreditMessageError;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.repository.Repository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreditUseCase implements CreditOperations {

   private final Repository<Credit> repository;

    public Mono<Credit> getById(String id){

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new AppException(CreditMessageError.CREDIT_NOT_EXIST.value)));
    }

    public Mono<Credit> update(String id, Deposit deposit){

        return Mono.just(deposit).flatMap(credit1 -> repository.findById(id))
                .switchIfEmpty(Mono.error(new AppException(CreditMessageError.CREDIT_NOT_EXIST.value)))
                .flatMap(creditDB -> updateCreditModel(deposit, creditDB))
                .filter(credit -> credit.depositGreaterThanDebt())
                .switchIfEmpty(Mono.error(new AppException(CreditMessageError.DEPOSIT_GREATER_THAN_DEBT.value)))
                .flatMap(repository::save)
                .filter(credit -> credit.isValid())
                .flatMap(credit -> this.deleteCredit(id).thenReturn(credit));
    }

    public Mono<Void> deleteCredit(String id){

        return this.getById(id)
                .flatMap(credit -> {
                    if (credit.isValid()){
                        return repository.deleteById(id);
                    }else {
                        return Mono.error(new AppException(CreditMessageError.REMAIN_DEBIT_PENDING.value));
                    }
                    }
                );
    }


}
