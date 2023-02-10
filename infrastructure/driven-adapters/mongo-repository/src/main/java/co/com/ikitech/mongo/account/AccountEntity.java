package co.com.ikitech.mongo.account;

import co.com.ikitech.mongo.credit.CreditEntity;
import lombok.AllArgsConstructor;
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
    private String password;
    private String names;
    private  String surNames;
    private String documentType;
    private String documentNumber;
    private String address;
    private String phone;
    private CreditEntity credit;
    private String status;
}
