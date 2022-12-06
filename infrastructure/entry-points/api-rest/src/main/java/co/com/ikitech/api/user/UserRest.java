package co.com.ikitech.api.user;

import co.com.ikitech.api.user.ikitech.IkiTechRestService;
import co.com.ikitech.model.user.error.UserMessageError;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.user.User;
import co.com.ikitech.usecase.user.*;
import lombok.*;
import org.mapstruct.factory.Mappers;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.*;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserRest extends IkiTechRestService<UserDTO, User> {
    private final UserUseCase useCase;

    private final UserMapper MAPPER = Mappers.getMapper(UserMapper.class);


    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Map<String, Object>>> createRecord(@RequestHeader(name = "Accept-Language", required = false)
                                                                      final Locale locale,
                                                                  @Valid @RequestBody UserDTO dto) {

        return Mono.just(dto)
                .flatMap(dataTransfer -> useCase.create(MAPPER.toEntity(dataTransfer)))
                .map(businessObject -> createResponseSuccess(businessObject,
                        MAPPER::toTransferObject));
    }

    @GetMapping(path = "/user")
    public Flux<User> getAll(){


        return useCase.getAll();
    }

    @GetMapping(path = "/user/{id}")
    public Mono<User> getUserById(@PathVariable String id){

        return useCase.getById(id);
    }

    @PutMapping(path = "/user/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> updateUser(@PathVariable String id, @RequestBody UserDTO dto){

        return Mono.just(dto).flatMap(dataTransfer -> useCase.update(id, MAPPER.toEntity(dataTransfer))
                .map(businessObject -> createResponseSuccess(businessObject,
                        MAPPER::toTransferObject)));
    }

    @DeleteMapping(path = "/user/{id}")
    public Mono<Void> delete(@PathVariable String id){

        return useCase.deleteUser(id);

    }

    @GetMapping(path = "/user/amount/{id}")
    public Mono<Integer> showAmount(@PathVariable String id){

        return useCase.showUserAmount(id);
    }
}
