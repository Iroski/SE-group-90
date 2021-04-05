package model.exception.database;

import model.exception.BaseException;

/**
 * @author ：Yubo Wang
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
