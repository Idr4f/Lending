package co.com.ikitech.model.user.exceptions;

//Invocacion de una Excepcion personalizada, y...ya
public class AppException extends RuntimeException  {
    public AppException(String message) {
        super(message);
    }
}
