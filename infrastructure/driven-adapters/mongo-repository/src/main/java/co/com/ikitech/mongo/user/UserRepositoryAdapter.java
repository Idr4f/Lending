package co.com.ikitech.mongo.user;

import co.com.ikitech.model.user.user.*;
import co.com.ikitech.mongo.helper.*;
import org.reactivecommons.utils.*;
import org.springframework.stereotype.*;

@Repository
public class UserRepositoryAdapter extends AdapterOperations<User, UserEntity, String, UserDBRepository>

 implements co.com.ikitech.model.user.repository.Repository<User>
{

    public UserRepositoryAdapter(UserDBRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, User.class));
    }

}
