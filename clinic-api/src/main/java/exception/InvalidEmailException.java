package exception;

import java.io.IOException;

public class InvalidEmailException extends IOException{

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable error) {
        super(message, error);
    }
}