package co.com.ikitech.usecase.user.account;

import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.model.user.account.AccountOperations;
import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.customer.Customer;
import co.com.ikitech.model.user.error.AccountMessageError;
import co.com.ikitech.model.user.error.UserMessageError;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.repository.Repository;
import reactor.core.publisher.*;

import javax.security.auth.login.AccountNotFoundException;

public class AccountUseCase implements AccountOperations {

    Repository<Account> repository;


    public Mono<Account> create(Account account){

        //return repository.save(account)
         //       .switchIfEmpty(Mono.error(new AppException(AccountMessageError.ACCOUNT_NOT_CREATE.value)));
        return Mono.just(account).flatMap(a -> saveAccount(a)).flatMap(a -> repository.save(a))
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

    public Mono<Void> delete(String id){

        return this.getById(id).flatMap(r -> repository.deleteById(id));
    }

    public Mono<Account> createCredit(String id, Credit credit){

        return Mono.just(credit).flatMap(user1 -> this.getById(id))
                .flatMap(userDB -> createCredit(credit, userDB))
                .flatMap(repository::save);
    }
}
