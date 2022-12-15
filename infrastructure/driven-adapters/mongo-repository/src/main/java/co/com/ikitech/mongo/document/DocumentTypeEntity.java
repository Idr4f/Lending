package co.com.ikitech.mongo.document;

//Entity del tipo de documento
public enum DocumentTypeEntity {
    CC("CC"),
    CE("CE"),
    PEP("PEP"),
    PAP("PAP"),
    NIT("NIT");
    public final String value;
    DocumentTypeEntity(String value){this.value = value;}
}
