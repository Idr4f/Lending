package co.com.ikitech.api.credit;

import lombok.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CreditDTO {

    private String id;
    private String dateLoan;
    private Long valueDisbursed;
    private String paymentDeadline;
    private Long interestValuation;
    private Long interestValue;
    private Long deposited;
    private Long remainingDebt;
    private String status;
}


