package co.com.ikitech.api.document;

//DTO del tipo de documento
public enum DocumentTypeDTO {
    CC("CC"),
    CE("CE"),
    PEP("PEP"),
    PAP("PAP"),
    NIT("NIT");
    public final String value;
    DocumentTypeDTO(String value){this.value = value;}
}
