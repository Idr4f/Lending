package co.com.ikitech.mongo.credit;

import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class CreditRepositoryAdapter extends AdapterOperations<Credit, CreditEntity, String, CreditDBRepository>

 implements co.com.ikitech.model.user.repository.Repository<Credit>
{

    public CreditRepositoryAdapter(CreditDBRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Credit.class));
    }


    @Override
    public Mono<Credit> findByNames(String name) {
        return null;
    }
}
