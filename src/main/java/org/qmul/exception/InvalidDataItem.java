package org.qmul.exception;

public class InvalidDataItem extends BaseException{
    public InvalidDataItem() {
        super();
    }

    public InvalidDataItem(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataItem(String message) {
        super(message);
    }

    public InvalidDataItem(Throwable cause) {
        super(cause);
    }
}
