package co.com.ikitech.mongo.customer;

import co.com.ikitech.mongo.credit.CreditEntity;
import co.com.ikitech.mongo.document.DocumentTypeEntity;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class CustomerEntity {

    @Id
    private String id;
    private String names;
    private  String surNames;
    private DocumentTypeEntity documentType;
    private String documentNumber;
    private String email;
    private String address;
    private String phone;
    private CreditEntity credit;
}
