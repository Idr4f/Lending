package co.com.ikitech.mongo.account;

import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.model.user.customer.Customer;
import co.com.ikitech.mongo.customer.CustomerDBRepository;
import co.com.ikitech.mongo.customer.CustomerEntity;
import co.com.ikitech.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryAdapter extends AdapterOperations<Account, AccountEntity, String, AccountDBRepository>

 implements co.com.ikitech.model.user.repository.Repository<Account>
{

    public AccountRepositoryAdapter(AccountDBRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Account.class));
    }


}
