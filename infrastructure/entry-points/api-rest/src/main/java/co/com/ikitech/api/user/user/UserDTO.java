package co.com.ikitech.api.user.user;

import co.com.ikitech.api.user.credit.CreditDTO;
import co.com.ikitech.api.user.document.DocumentTypeDTO;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {
    private String id;
    private String names;
    private  String surNames;
    private DocumentTypeDTO documentType;
    private String documentNumber;
    private String email;
    private String address;
    private String phone;
    private CreditDTO credit;
}
