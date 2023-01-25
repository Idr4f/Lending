package co.com.ikitech.mongo.customer;
import co.com.ikitech.mongo.document.DocumentTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
