package co.com.ikitech.model.user.credit;

import reactor.core.publisher.*;
import java.time.*;
import java.time.format.*;

//operaciones a realizar con el model, sin usar directamente el model
public interface CreditOperations {

    default Mono<Credit> updateCreditModel(Credit credit, Credit creditDB){

//        Long c, v;
//
//        if(creditDB.getRemainingDebt()!= null && credit.getDeposited() != null){
//            c = creditDB.getRemainingDebt() - credit.getDeposited();
//        }else {
//            c = creditDB.getRemainingDebt();
//            v = 0L;
//        }
//
//        if (creditDB.getDeposited() != null){
//            v = credit.getDeposited() + creditDB.getDeposited();
//        }else if(creditDB.getDeposited()==null){
//            v = credit.getDeposited();
//        } else if (credit.getDeposited()==null) {
//            v = creditDB.getDeposited();
//        }else {
//            v = 0L;
//        }

        return Mono.just(creditDB
                .toBuilder()
                        .id(credit.getId())
                        .dateLoan(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                        .valueDisbursed(creditDB.getValueDisbursed())
                        .paymentDeadline(creditDB.getPaymentDeadline())
                        .interestValuation(creditDB.getInterestValuation())
                        .interestValue(creditDB.getValueDisbursed() + creditDB.getInterestValuation()/100)
                        .deposited(creditDB.getDeposited() + credit.getDeposited())
                        .remainingDebt(creditDB.getRemainingDebt() - credit.getDeposited())
                .build());
    }


}
