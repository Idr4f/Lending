package co.com.ikitech.mongo.user;

import co.com.ikitech.model.user.user.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface UserDBRepository extends ReactiveMongoRepository<UserEntity, String>, ReactiveQueryByExampleExecutor<UserEntity> {

}
