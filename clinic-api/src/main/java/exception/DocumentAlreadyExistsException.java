package exception;

import java.io.IOException;

public class DocumentAlreadyExistsException extends IOException {
    public DocumentAlreadyExistsException(String message) {
        super(message);
    }

    public DocumentAlreadyExistsException(String message, Throwable error) {
        super(message, error);
    }
}
