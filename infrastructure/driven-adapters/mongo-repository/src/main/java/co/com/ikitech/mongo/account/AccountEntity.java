package co.com.ikitech.mongo.account;

import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.customer.Customer;
import co.com.ikitech.mongo.credit.CreditEntity;
import co.com.ikitech.mongo.customer.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
public class AccountEntity {

    @Id
    private String id;
    private String type;
    private String nickName;
    private String email;
    private String openDate;
    private String status;
    private String password;
    private CustomerEntity customer;
    private CreditEntity credit;
}
