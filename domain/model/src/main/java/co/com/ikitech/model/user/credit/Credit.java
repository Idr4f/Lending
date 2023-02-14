package co.com.ikitech.model.user.credit;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Credit {

    private String id;
    private String dateLoan;
    private Long valueDisbursed;
    private String paymentDeadline;
    private Long interestValuation;
    private Long interestValue;
    private Long deposited;
    private Long remainingDebt;
    private String status;

    public Boolean isValid(){
        return getRemainingDebt() == 0;
    }

    public Boolean depositGreaterThanDebt(){

        return getRemainingDebt() >= 0;
    }
}
