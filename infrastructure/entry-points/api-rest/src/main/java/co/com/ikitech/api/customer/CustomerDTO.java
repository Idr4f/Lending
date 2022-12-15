package co.com.ikitech.api.customer;

import co.com.ikitech.api.credit.CreditDTO;
import co.com.ikitech.api.document.DocumentTypeDTO;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerDTO {
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
