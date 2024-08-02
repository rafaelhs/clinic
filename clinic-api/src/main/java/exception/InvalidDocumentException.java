package exception;

import java.io.IOException;

public class InvalidDocumentException extends IOException {
    public InvalidDocumentException(String message) {
        super(message);
    }

    public InvalidDocumentException(String message, Throwable error) {
        super(message, error);
    }
}
