package co.com.ikitech.model.user.document;

public enum DocumentType {

    CC("CC"),
    CE("CE"),
    PEP("PEP"),
    PAP("PAP");

    public final String value;

    DocumentType(String value){this.value = value;}
}
