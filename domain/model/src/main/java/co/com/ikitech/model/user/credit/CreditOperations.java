package co.com.ikitech.model.user.credit;

import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.model.user.error.CreditMessageError;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.repository.Repository;
import reactor.core.publisher.*;

import java.time.*;
import java.time.format.*;

//operaciones a realizar con el model, sin usar directamente el model
public interface CreditOperations {

    default Mono<Credit> updateCreditModel(Deposit deposit, Credit creditDB) {

        return Mono.just(creditDB
                .toBuilder()
                .id(creditDB.getId())
                .dateLoan(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .valueDisbursed(creditDB.getValueDisbursed())
                .paymentDeadline(creditDB.getPaymentDeadline())
                .interestValuation(creditDB.getInterestValuation())
                .interestValue(creditDB.getValueDisbursed() * creditDB.getInterestValuation() / 100)
                .deposited(this.deposited(creditDB, deposit))
                .remainingDebt(creditDB.getRemainingDebt() - deposit.getDeposit())
                .status("Updated")
                .build());
    }

    default Long deposited(Credit creditDB, Deposit deposit) {

        if (creditDB.getStatus() == "Created") {

            return deposit.getDeposit();
        } else if (creditDB.getStatus() == "Updated") {

            return creditDB.getDeposited() + deposit.getDeposit();
        } else {

            return deposit.getDeposit();
        }
    }
}
