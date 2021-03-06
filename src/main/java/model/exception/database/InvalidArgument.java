package model.exception.database;

import model.exception.BaseException;

public class InvalidArgument extends BaseException {

    public InvalidArgument() {
        super();
    }

    public InvalidArgument(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgument(String message) {
        super(message);
    }

    public InvalidArgument(Throwable cause) {
        super(cause);
    }
}
