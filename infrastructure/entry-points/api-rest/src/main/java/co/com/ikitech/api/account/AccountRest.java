package co.com.ikitech.api.account;

import co.com.ikitech.api.credit.CreditDTO;
import co.com.ikitech.api.credit.CreditMapper;
import co.com.ikitech.api.customer.CustomerDTO;
import co.com.ikitech.api.customer.CustomerMapper;
import co.com.ikitech.api.ikitech.IkiTechRestService;
import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.model.user.customer.Customer;
import co.com.ikitech.usecase.user.account.AccountUseCase;
import co.com.ikitech.usecase.user.customer.CustomerUseCase;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountRest extends IkiTechRestService<AccountDTO, Account> {
    private final AccountUseCase useCase;

    private final AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);
    private final CreditMapper MAP = Mappers.getMapper(CreditMapper.class);


    @PostMapping(path = "/account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Map<String, Object>>> createAccount(@RequestHeader(name = "Accept-Language", required = false)
                                                                      final Locale locale,
                                                                  @Valid @RequestBody AccountDTO dto) {

        return Mono.just(dto)
                .flatMap(dataTransfer -> useCase.create(MAPPER.toEntity(dataTransfer)))
                .map(businessObject -> createResponseSuccess(businessObject,
                        MAPPER::toTransferObject));
    }

    @GetMapping(path = "/account/{id}")
    public Mono<Account> getUserById(@PathVariable String id){

        return useCase.getById(id);
    }

    @GetMapping(path = "/user")
    public Flux<Account> getAll(){

        return useCase.getAll();
    }

    @PutMapping(path = "/account/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> updateUser(@PathVariable String id, @RequestBody AccountDTO dto){

        return Mono.just(dto).flatMap(dataTransfer -> useCase.update(id, MAPPER.toEntity(dataTransfer))
                .map(businessObject -> createResponseSuccess(businessObject,
                        MAPPER::toTransferObject)));
    }

    @PostMapping (path = "/account/{id}/credit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Account> createCredit(@PathVariable String id, @RequestBody CreditDTO dto){

        return Mono.just(dto).flatMap(dataTransfer -> useCase.createCredit(id, MAP.toEntityCredit(dto)));
    }

    @DeleteMapping(path = "/account/{id}")
    public Mono<Void> delete(@PathVariable String id){

        return useCase.delete(id);

    }
}
