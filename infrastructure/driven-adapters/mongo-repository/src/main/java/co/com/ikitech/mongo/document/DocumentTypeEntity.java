package co.com.ikitech.mongo.document;

public enum DocumentTypeEntity {

    CC("CC"),
    CE("CE"),
    PEP("PEP"),
    PAP("PAP");

    public final String value;

    DocumentTypeEntity(String value){this.value = value;}
}
