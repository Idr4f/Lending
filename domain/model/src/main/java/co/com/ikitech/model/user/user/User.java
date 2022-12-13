package co.com.ikitech.model.user.user;

import co.com.ikitech.model.user.document.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
//Modelo principal de los datos del usuario
public class User {

    private String id;
    private String names;
    private  String surNames;
    private DocumentType documentType;
    private String documentNumber;
    private String email;
    private String address;
    private String phone;
}
