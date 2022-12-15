package co.com.ikitech.model.user.document;

//Enum de los tipos de documentos que se permiten en colombia
public enum DocumentType {
    CC("CC"),
    CE("CE"),
    PEP("PEP"),
    PAP("PAP"),
    NIT("NIT");

    public final String value;
    DocumentType(String value){this.value = value;}
}