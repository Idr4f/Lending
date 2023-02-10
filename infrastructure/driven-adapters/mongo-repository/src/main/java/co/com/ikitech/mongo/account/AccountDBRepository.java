package co.com.ikitech.mongo.account;

import co.com.ikitech.model.user.account.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface AccountDBRepository extends ReactiveMongoRepository<AccountEntity, String>, ReactiveQueryByExampleExecutor<AccountEntity> {
    Mono<Account> getByNames(String names);
}