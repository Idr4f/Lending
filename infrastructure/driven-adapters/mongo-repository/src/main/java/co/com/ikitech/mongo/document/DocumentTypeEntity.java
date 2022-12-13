package co.com.ikitech.mongo.document;

//Entity del tipo de documento
public enum DocumentTypeEntity {
    CC("Cedula de Ciudadanía"),
    CE("Cedula De Extranjería"),
    PEP("Permiso Especial De Permanencia"),
    PAP("Pasaporte"),
    NIT("Número De Identificacion Tributaria");
    public final String value;
    DocumentTypeEntity(String value){this.value = value;}
}
