package co.com.ikitech.model.user.account;

import co.com.ikitech.model.user.credit.Credit;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface AccountOperations {

    default Mono<Account> updateModel(Account account, Account accountDB) {

        return Mono.just(accountDB
                .toBuilder()
                        .id(account.getId())
                        .type(account.getType())
                        .nickName(account.getNickName())
                        .email(account.getEmail())
                        .openDate(account.getOpenDate())
                        .status(account.getStatus())
                        .password(account.getPassword())
                        .customer(account.getCustomer())
                .build());
    }

    default Mono<Account> createCredit(Credit credit, Account accountDB) {

        return Mono.just(accountDB.
                toBuilder()
                .id(accountDB.getId())
                .type(accountDB.getType())
                .nickName(accountDB.getNickName())
                .email(accountDB.getEmail())
                .openDate(accountDB.getOpenDate())
                .status(accountDB.getStatus())
                .customer(accountDB.getCustomer())
                .credit(credit.toBuilder()
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
