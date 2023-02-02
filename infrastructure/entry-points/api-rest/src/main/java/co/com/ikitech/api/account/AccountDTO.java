package co.com.ikitech.api.account;

import co.com.ikitech.api.credit.CreditDTO;
import co.com.ikitech.api.customer.CustomerDTO;
import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AccountDTO {

    private String id;
    private String type;
    private String nickName;
    private String email;
    private String openDate;
    private String status;
    private String password;
    private CustomerDTO customer;
    private CreditDTO credit;
}
