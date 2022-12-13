package co.com.ikitech.mongo.user;

import co.com.ikitech.model.user.document.DocumentType;
import co.com.ikitech.mongo.document.DocumentTypeEntity;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class UserEntity {

    @Id
    private String id;
    private String names;
    private  String surNames;
    private DocumentTypeEntity documentTypeEntity;
    private String documentNumber;
    private String email;
    private String address;
    private String phone;
}
