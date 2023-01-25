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
@CrossOrigin("*")
public class CustomerRest extends IkiTechRestService<CustomerDTO, Customer> {
    private final CustomerUseCase useCase;

    @GetMapping(path = "/user")
    public Flux<Customer> getAll(){

        return useCase.getAll();
    }

    @GetMapping(path = "/user/{id}")
    public Mono<Customer> getUserById(@PathVariable String id){

        return useCase.getById(id);
    }
}
