package co.com.ikitech.mongo.customer;

import co.com.ikitech.model.user.customer.*;
import co.com.ikitech.mongo.helper.*;
import org.reactivecommons.utils.*;
import org.springframework.stereotype.*;

@Repository
public class CustomerRepositoryAdapter extends AdapterOperations<Customer, CustomerEntity, String, CustomerDBRepository>

 implements co.com.ikitech.model.user.repository.Repository<Customer>
{

    public CustomerRepositoryAdapter(CustomerDBRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Customer.class));
    }


}
