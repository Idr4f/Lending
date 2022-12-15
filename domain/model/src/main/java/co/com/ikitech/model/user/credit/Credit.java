package co.com.ikitech.model.user.credit;

import lombok.*;
import java.util.Date;

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
}
