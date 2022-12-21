package co.com.ikitech.model.user.error;

public enum AccountMessageError {

    ACCOUNT_NOT_EXIST("account.accountNotExist"),

    ACCOUNT_NOT_CREATE("account.accountNotCreate"),

    NO_ACCOUNT_CREATED("account.notAccountsCreated");

    public final String value;

    AccountMessageError(String value){this.value = value;}
}
