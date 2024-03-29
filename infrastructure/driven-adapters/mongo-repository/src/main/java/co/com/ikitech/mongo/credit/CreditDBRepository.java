package co.com.ikitech.mongo.credit;

import co.com.ikitech.mongo.customer.CustomerEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface CreditDBRepository extends ReactiveMongoRepository<CreditEntity, String>, ReactiveQueryByExampleExecutor<CreditEntity> {

}
