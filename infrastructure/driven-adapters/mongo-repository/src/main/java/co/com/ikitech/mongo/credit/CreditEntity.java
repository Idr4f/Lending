package co.com.ikitech.mongo.credit;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "credit")
public class CreditEntity {

    @Id
    private String id;
    private String dateLoan;
    private Long valueDisbursed;
    private String paymentDeadline;
    private Long interestValuation;
    private Long interestValue;
}
