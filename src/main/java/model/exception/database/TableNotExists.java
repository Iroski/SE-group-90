package model.exception.database;

import model.exception.BaseException;

/**
 * @author ：Yubo Wang
 * @date ：2021-04-04 15:57
 * @description：
 * @modified By：
 * @version:
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
