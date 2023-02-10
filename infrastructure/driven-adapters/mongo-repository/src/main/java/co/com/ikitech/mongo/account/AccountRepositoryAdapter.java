package co.com.ikitech.mongo.account;

import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class AccountRepositoryAdapter extends AdapterOperations<Account, AccountEntity, String, AccountDBRepository>

 implements co.com.ikitech.model.user.repository.Repository<Account>
{

    public AccountRepositoryAdapter(AccountDBRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Account.class));
    }


    @Override
    public Mono<Account> findByNames(String names) {
        return repository.getByNames(names);
    }
}
