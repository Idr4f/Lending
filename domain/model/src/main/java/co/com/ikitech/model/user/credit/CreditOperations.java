package co.com.ikitech.model.user.credit;

import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//operaciones a realizar con el model, sin usar directamente el model
public interface CreditOperations {

    default Mono<Credit> updateCreditModel(Credit credit, Credit creditDB){


        return Mono.just(creditDB
                .toBuilder()
                        .id(credit.getId())
                        .dateLoan(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                        .valueDisbursed(creditDB.getValueDisbursed())
                        .paymentDeadline(creditDB.getPaymentDeadline())
                        .interestValuation(creditDB.getInterestValuation())
                        .interestValue(creditDB.getValueDisbursed() + creditDB.getInterestValuation()/100)
                .build());
    }


}
