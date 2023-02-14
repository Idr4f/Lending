package co.com.ikitech.usecase.user.account;

import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.model.user.account.AccountOperations;
import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.error.AccountMessageError;
import co.com.ikitech.model.user.error.CreditMessageError;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.repository.Repository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
public class AccountUseCase implements AccountOperations {

    private final Repository<Account> repository;
    private final Repository<Credit> creditRepository;
    private final Repository<Credit> repo;

    public Mono<Account> create(Account account){

             return Mono.just(account)
                     .flatMap(account1 -> saveAccount(account))
                     .flatMap(repository::save)
                     .switchIfEmpty(Mono.error(new AppException(AccountMessageError.ACCOUNT_NOT_CREATE.value)));
    }

    public Mono<Account> getById(String id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new AppException(AccountMessageError.ACCOUNT_NOT_EXIST.value)));
    }

    public Flux<Account> getAll() {

        return repository.findAll()
               .switchIfEmpty(Mono.error(new AppException(AccountMessageError.NO_ACCOUNTS_CREATED.value)));
    }

    public Mono<Account> update(String id, Account account) {

        return Mono.just(account).flatMap(credit1 -> this.getById(id))
                .flatMap(accountDB -> updateModel(account, accountDB))
                .flatMap(repository::save);
    }
    public Mono<String> delete(String id){

        return this.getById(id)
                .filter(account -> account.getCredit().isValid())
                .switchIfEmpty(Mono.error(new Exception(CreditMessageError.REMAIN_DEBIT_PENDING.value)))
                .flatMap(account -> creditRepository.deleteById(account.getCredit().getId()).thenReturn(account))
                .flatMap(account -> repository.deleteById(account.getId()).thenReturn(account))
                .map(account -> "La cuenta se elimino con exito");
    }
    public Mono<Credit> createCredit(String id, Credit credit){

        return Mono.just(credit).flatMap(user1 -> this.getById(id))
                .flatMap(userDB -> createCredit(credit, userDB))
                .flatMap(repository::save)
                .flatMap(account -> repo.save(account.getCredit()))
                .switchIfEmpty(Mono.error(new AppException(CreditMessageError.CREDIT_NOT_CREATE.value)));
    }

    public Mono<Account> getByName(String names){

        return repository.findByNames(names).switchIfEmpty(Mono.error(new AppException(AccountMessageError.ACCOUNT_NOT_EXIST.value)));
    }
}
