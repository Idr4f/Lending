package co.com.ikitech.model.user.account;

import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.customer.Customer;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Account {

    private String id;
    private String type;
    private String nickName;
    private String email;
    private String openDate;
    private String status;
    private String password;
    private Customer customer;
    private Credit credit;
}
