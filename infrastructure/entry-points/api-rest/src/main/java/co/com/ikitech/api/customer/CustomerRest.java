package co.com.ikitech.api.customer;

import co.com.ikitech.api.credit.CreditDTO;
import co.com.ikitech.api.credit.CreditMapper;
import co.com.ikitech.api.ikitech.IkiTechRestService;
import co.com.ikitech.model.user.customer.Customer;
import co.com.ikitech.usecase.user.customer.CustomerUseCase;
import lombok.*;
import org.mapstruct.factory.Mappers;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CustomerRest extends IkiTechRestService<CustomerDTO, Customer> {
    private final CustomerUseCase useCase;

    private final CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);
    private final CreditMapper MAP = Mappers.getMapper(CreditMapper.class);


    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)

    public Mono<ResponseEntity<Map<String, Object>>> createRecord(@RequestHeader(name = "Accept-Language", required = false)
                                                                      final Locale locale,
                                                                  @Valid @RequestBody CustomerDTO dto) {

        return Mono.just(dto)
                .flatMap(dataTransfer -> useCase.create(MAPPER.toEntity(dataTransfer)))
                .map(businessObject -> createResponseSuccess(businessObject,
                        MAPPER::toTransferObject));
    }

    @GetMapping(path = "/user")
    public Flux<Customer> getAll(){

        return useCase.getAll();
    }

    @GetMapping(path = "/user/{id}")
    public Mono<Customer> getUserById(@PathVariable String id){

        return useCase.getById(id);
    }

    @PutMapping(path = "/user/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> updateUser(@PathVariable String id, @RequestBody CustomerDTO dto){

        return Mono.just(dto).flatMap(dataTransfer -> useCase.update(id, MAPPER.toEntity(dataTransfer))
                .map(businessObject -> createResponseSuccess(businessObject,
                        MAPPER::toTransferObject)));
    }

    @DeleteMapping(path = "/user/{id}")
    public Mono<Void> delete(@PathVariable String id){

        return useCase.deleteUser(id);

    }

    @PostMapping (path = "/user/credit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Customer> createCredit(@PathVariable String id, @RequestBody CreditDTO dto){

        return Mono.just(dto).flatMap(dataTransfer -> useCase.createUserCredit(id, MAP.toEntityCredit(dto)));
    }
}
