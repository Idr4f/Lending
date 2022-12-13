package co.com.ikitech.api.user;

//DTO del tipo de documento
public enum DocumentTypeDTO {
    CC("Cedula de Ciudadanía"),
    CE("Cedula De Extranjería"),
    PEP("Permiso Especial De Permanencia"),
    PAP("Pasaporte"),
    NIT("Número De Identificacion Tributaria");
    public final String value;
    DocumentTypeDTO(String value){this.value = value;}
}
