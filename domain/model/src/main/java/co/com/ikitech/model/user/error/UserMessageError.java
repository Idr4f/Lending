package co.com.ikitech.model.user.error;

public enum UserMessageError {

    USER_NOT_EXIST("user.userNotExist"),

    USER_NOT_CREATE("user.userNotCreate"),

    NO_USERS_CREATED("user.notUsersCreated");

    public final String value;

    UserMessageError(String value){this.value = value;}
}
