package co.com.ikitech.mongo.account;

import co.com.ikitech.mongo.customer.CustomerEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface AccountDBRepository extends ReactiveMongoRepository<AccountEntity, String>, ReactiveQueryByExampleExecutor<AccountEntity> {

}
