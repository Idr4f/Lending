package co.com.ikitech.api.credit;

import co.com.ikitech.api.ikitech.IkiTechRestService;
import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.usecase.user.credit.CreditUseCase;
import lombok.*;
import org.mapstruct.factory.Mappers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CrossOrigin("*")
public class CreditRest extends IkiTechRestService<CreditDTO, Credit> {
    private final CreditUseCase useCase;

    private final CreditMapper MAP = Mappers.getMapper(CreditMapper.class);

    @GetMapping(path = "/credit/{id}")
    public Mono<Credit> getCreditById(@PathVariable String id){

        return useCase.getById(id);
    }

    @DeleteMapping(path = "/credit/{id}")
    public Mono<Void> delete(@PathVariable String id){

        return useCase.deleteCredit(id);
    }

    @PutMapping(path = "/credit/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> updateCredit(@PathVariable String id, @RequestBody CreditDTO dto){

        return Mono.just(dto).flatMap(dataTransfer -> useCase.update(id, MAP.toEntityCredit(dataTransfer))
                .map(businessObject -> createResponseSuccess(businessObject,
                        MAP::toTransferObjectCredit)));
    }

}
