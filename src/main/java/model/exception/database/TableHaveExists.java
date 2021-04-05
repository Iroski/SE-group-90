package model.exception.database;

import model.exception.BaseException;

/**
 * @author ：Yubo Wang
 */
public class TableHaveExists extends BaseException {
    public TableHaveExists() {
        super();
    }

    public TableHaveExists(String message, Throwable cause) {
        super(message, cause);
    }

    public TableHaveExists(String message) {
        super(message);
    }

    public TableHaveExists(Throwable cause) {
        super(cause);
    }
}
