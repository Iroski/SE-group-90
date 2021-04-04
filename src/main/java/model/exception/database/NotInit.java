package model.exception.database;

import model.exception.BaseException;

/**
 * @author ：Yubo Wang
 * @date ：2021-04-04 13:58
 * @description：
 * @modified By：
 * @version:
 */
public class NotInit extends BaseException {

        public NotInit() {
            super();
        }

        public NotInit(String message, Throwable cause) {
            super(message, cause);
        }

        public NotInit(String message) { super(message); }

        public NotInit(Throwable cause) {
            super(cause);
        }
}
