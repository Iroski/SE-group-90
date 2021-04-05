package model.exception.database;

import model.exception.BaseException;

/**
 * @author ：Yubo Wang
 */
public class TableNotExists extends BaseException {
    public TableNotExists() {
        super();
    }

    public TableNotExists(String message, Throwable cause) {
        super(message, cause);
    }

    public TableNotExists(String message) {
        super(message);
    }

    public TableNotExists(Throwable cause) {
        super(cause);
    }
}
