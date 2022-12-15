package co.com.ikitech.mongo.customer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface CustomerDBRepository extends ReactiveMongoRepository<CustomerEntity, String>, ReactiveQueryByExampleExecutor<CustomerEntity> {

}
