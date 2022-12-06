package co.com.ikitech.mongo.user;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface UserDBRepository extends ReactiveMongoRepository<UserEntity, String>, ReactiveQueryByExampleExecutor<UserEntity> {}
