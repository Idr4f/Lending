package co.com.ikitech.model.user.document;

//Enum de los tipos de documentos que se permiten en colombia
public enum DocumentType {
    CC("Cedula de Ciudadanía"),
    CE("Cedula De Extranjería"),
    PEP("Permiso Especial De Permanencia"),
    PAP("Pasaporte"),
    NIT("Número De Identificacion Tributaria");
    public final String value;
    DocumentType(String value){this.value = value;}
}