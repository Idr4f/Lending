package co.com.ikitech.model.user.error;

public enum AccountMessageError {

    ACCOUNT_NOT_EXIST("account.accountNotExist"),
    ACCOUNT_NOT_CREATE("account.accountNotCreate"),
    INVALID_EMAIL_FORMAT("account.invalidEmailFormat"),
    NO_ACCOUNTS_CREATED("account.notAccountsCreated");

    public final String value;

    AccountMessageError(String value){this.value = value;}
}
