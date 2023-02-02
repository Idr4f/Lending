package co.com.ikitech.model.user.customer;

import co.com.ikitech.model.user.document.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

public class Customer {

    private String id;
    private String names;
    private  String surNames;
    private DocumentType documentType;
    private String documentNumber;
    private String address;
    private String phone;
}
