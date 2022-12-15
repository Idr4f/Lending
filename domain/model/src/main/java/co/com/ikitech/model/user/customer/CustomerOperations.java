package co.com.ikitech.model.user.customer;

import co.com.ikitech.model.user.credit.Credit;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//operaciones a realizar con el model, sin usar directamente el model
public interface CustomerOperations {

    default Mono<Customer> updateModel(Customer user, Customer userDB){

        return Mono.just(userDB
                .toBuilder()
                        .id(userDB.getId())
                        .names(user.getNames())
                        .surNames(user.getSurNames())
                        .documentType(user.getDocumentType())
                        .documentNumber(user.getDocumentNumber())
                        .email(user.getEmail())
                        .address(user.getAddress())
                        .phone(user.getPhone())
                        .credit(userDB.getCredit())
                .build());
    }

    default Mono<Customer> createCredit(Credit credit, Customer userDB){

        return Mono.just(userDB
                .toBuilder()
                        .id(userDB.getId())
                        .names(userDB.getNames())
                        .surNames(userDB.getSurNames())
                        .documentType(userDB.getDocumentType())
                        .documentNumber(userDB.getDocumentNumber())
                        .email(userDB.getEmail())
                        .address(userDB.getAddress())
                        .phone(userDB.getPhone())
                        .credit(credit
                                .toBuilder()
                                    .id(credit.getId())
                                    .dateLoan(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")))
                                    .valueDisbursed(credit.getValueDisbursed())
                                    .paymentDeadline(credit.getPaymentDeadline())
                                    .interestValuation(credit.getInterestValuation())
                                    .interestValue(credit.getValueDisbursed() + credit.getInterestValuation()/100)
                                    .deposited(0L)
                                    .remainingDebt(credit.getValueDisbursed())
                                .build())
                .build());
    }
}
