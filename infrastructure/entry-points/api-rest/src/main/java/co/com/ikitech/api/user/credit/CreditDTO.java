package co.com.ikitech.api.user.credit;

import lombok.*;
import java.util.Date;

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
}