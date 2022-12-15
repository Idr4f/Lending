package co.com.ikitech.model.user.credit;

import reactor.core.publisher.Mono;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//operaciones a realizar con el model, sin usar directamente el model
public interface CreditOperations {

    default Mono<Credit> updateCreditModel(Credit credit, Credit creditDB){


        return Mono.just(creditDB
                .toBuilder()
                        .id(credit.getId())
                        .dateLoan(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                        .valueDisbursed(creditDB.getValueDisbursed())
                        .paymentDeadline(LocalDateTime.parse(String.valueOf(creditDB.getPaymentDeadline())).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                        .interestValuation(creditDB.getInterestValuation())
                        .interestValue(creditDB.getValueDisbursed() + creditDB.getInterestValuation()/100)
                .build());
    }


}
