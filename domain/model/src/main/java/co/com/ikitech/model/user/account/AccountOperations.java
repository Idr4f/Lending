package co.com.ikitech.model.user.account;

import co.com.ikitech.model.user.credit.Credit;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface AccountOperations {

    default Mono<Account> updateModel(Account account, Account accountDB) {

        return Mono.just(accountDB
                .toBuilder()
                        .id(accountDB.getId())
                        .type(account.getType())
                        .nickName(account.getNickName())
                        .email(account.getEmail())
                        .openDate(accountDB.getOpenDate())
                        .password(account.getPassword())
                        .names(account.getNames())
                        .surNames(account.getSurNames())
                        .documentType(account.getDocumentType())
                        .documentNumber(account.getDocumentNumber())
                        .address(account.getAddress())
                        .phone(account.getPhone())
                        .status("Updated")
                .build());
    }



    default Mono<Account> saveAccount(Account account){
        return
        Mono.just(account.toBuilder()
                .openDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")))
                .status("Created")
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
                .password(accountDB.getPassword())
                .names(accountDB.getNames())
                .surNames(accountDB.getSurNames())
                .documentType(accountDB.getDocumentType())
                .documentNumber(accountDB.getDocumentNumber())
                .address(accountDB.getAddress())
                .phone(accountDB.getPhone())
                .credit(credit.toBuilder()
                        .id(credit.getId())
                        .dateLoan(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")))
                        .valueDisbursed(credit.getValueDisbursed())
                        .paymentDeadline(credit.getPaymentDeadline())
                        .interestValuation(credit.getInterestValuation())
                        .interestValue(credit.getValueDisbursed() * credit.getInterestValuation()/100)
                        .deposited(0L)
                        .remainingDebt(credit.getValueDisbursed() + (credit.getValueDisbursed() * credit.getInterestValuation()/100))
                        .status("Created")
                        .build())
                .status(accountDB.getStatus())
                .build());
    }


}
