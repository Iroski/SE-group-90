package model.exception.database;

import model.exception.BaseException;

/**
 * @author ：Yubo Wang
 * @date ：2021-04-04 14:19
 * @description：
 * @modified By：
 * @version:
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
