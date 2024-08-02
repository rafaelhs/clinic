package exception;

import java.io.IOException;

public class EmailAlreadyExistsException extends IOException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable error) {
        super(message, error);
    }
}
