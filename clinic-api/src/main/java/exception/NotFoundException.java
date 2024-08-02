package exception;

import java.io.IOException;

public class NotFoundException extends IOException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable error) {
        super(message, error);
    }
}
