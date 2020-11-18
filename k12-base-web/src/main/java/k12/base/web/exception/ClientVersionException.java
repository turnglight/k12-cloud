package k12.base.web.exception;

public class ClientVersionException extends RuntimeException{

    public ClientVersionException(){
        super();
    }

    public ClientVersionException(String message){
        super(message);
    }

    public ClientVersionException(Throwable cause) {
        super(cause);
    }

    public ClientVersionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientVersionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
