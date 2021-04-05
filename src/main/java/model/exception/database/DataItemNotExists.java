package model.exception.database;

import model.exception.BaseException;

/**
 * @author ：Yubo Wang
 */
public class DataItemNotExists extends BaseException {
    public DataItemNotExists() {
        super();
    }

    public DataItemNotExists(String message, Throwable cause) {
        super(message, cause);
    }

    public DataItemNotExists(String message) { super(message); }

    public DataItemNotExists(Throwable cause) {
        super(cause);
    }
}
