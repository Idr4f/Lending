package co.com.ikitech.api.account;

import co.com.ikitech.api.credit.CreditDTO;
import lombok.*;

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
    private String password;
    private String names;
    private  String surNames;
    private String documentType;
    private String documentNumber;
    private String address;
    private String phone;
    private CreditDTO credit;
    private String status;
}
