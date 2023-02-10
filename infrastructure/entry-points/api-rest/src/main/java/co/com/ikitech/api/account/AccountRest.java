package co.com.ikitech.api.account;

import co.com.ikitech.api.credit.CreditDTO;
import co.com.ikitech.api.credit.CreditMapper;
import co.com.ikitech.api.ikitech.IkiTechRestService;
import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.usecase.user.account.AccountUseCase;
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
@CrossOrigin(origins = "*")
public class AccountRest extends IkiTechRestService<AccountDTO, Account> {
    private final AccountUseCase useCase;
    private final AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);
    private final CreditMapper MAP = Mappers.getMapper(CreditMapper.class);

    @PostMapping(path = "/account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Map<String, Object>>> createAccount(@RequestHeader(name = "Accept-Language", required = false)
                                                                      final Locale locale,
                                                                  @Valid @RequestBody AccountDTO dto) {

        return Mono.just(dto).flatMap(dataTransfer -> useCase.create(MAPPER.toEntity(dto)))
                .map(businessObject -> createResponseSuccess(businessObject,
                        MAPPER::toTransferObject));
    }

    @GetMapping(path = "/account/{id}")
    public Mono<Account> getUserById(@PathVariable String id){

        return useCase.getById(id);
    }

    @GetMapping(path = "/account/{name}")
    public Mono<Account> findByName(@PathVariable String names){

        return useCase.getByName(names);
    }

    @GetMapping(path = "/account")
    public Flux<Account> getAll(){

        return useCase.getAll();
    }

    @PutMapping(path = "/account/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Map<String, Object>>> updateUser(@PathVariable String id, @RequestBody AccountDTO dto){

        return Mono.just(dto).flatMap(dataTransfer -> useCase.update(id, MAPPER.toEntity(dataTransfer))
                .map(businessObject -> createResponseSuccess(businessObject,
                        MAPPER::toTransferObject)));
    }
    @PutMapping(path = "/account/{id}/credit",  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Credit> createCredit(@PathVariable String id, @RequestBody CreditDTO dto){


        return Mono.just(dto).flatMap(dataTransfer -> useCase.createCredit( id, MAP.toEntityCredit(dataTransfer)));
    }
    @DeleteMapping(path = "/account/{id}")
    public Mono<Void> delete(@PathVariable String id){

             return useCase.delete(id);
    }
}
