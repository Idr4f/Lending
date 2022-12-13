package co.com.ikitech.api.user;

import co.com.ikitech.model.user.document.*;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {
    private String id;
    private String names;
    private  String surNames;
    private DocumentType documentTypeDTO;
    private String documentNumber;
    private String email;
    private String address;
    private String phone;
}
