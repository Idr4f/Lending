package co.com.ikitech.api.user;

import co.com.ikitech.model.user.document.DocumentType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {

    private String id;
    private String names;
    private  String surNames;
    private DocumentType documentType;
    private String documentNumber;
    private String email;
    private String address;
    private String phone;
    private Integer amount;
}
