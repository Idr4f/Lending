package co.com.ikitech.model.user.error;

public enum CreditMessageError {

    CREDIT_NOT_EXIST("credit.creditNotExist"),

    CREDIT_NOT_CREATE("credit.creditNotCreate"),

    NO_CREDITS_CREATED("CREDIT.notCreditsCreated"),

    REMAIN_DEBIT_PENDING("credit.remainDebitPending");

    public final String value;

    CreditMessageError(String value){this.value = value;}
}
