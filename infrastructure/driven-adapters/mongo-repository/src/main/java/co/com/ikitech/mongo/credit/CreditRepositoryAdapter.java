package co.com.ikitech.mongo.credit;

import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.customer.Customer;
import co.com.ikitech.mongo.customer.CustomerDBRepository;
import co.com.ikitech.mongo.customer.CustomerEntity;
import co.com.ikitech.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CreditRepositoryAdapter extends AdapterOperations<Credit, CreditEntity, String, CreditDBRepository>

 implements co.com.ikitech.model.user.repository.Repository<Credit>
{

    public CreditRepositoryAdapter(CreditDBRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Credit.class));
    }


}
