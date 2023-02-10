package co.com.ikitech.model.user.account;

import co.com.ikitech.model.user.credit.Credit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String password;
    private String names;
    private String surNames;
    private String documentType;
    private String documentNumber;
    private String address;
    private String phone;
    private Credit credit;

    private String status;
}
