package model.exception.database;

import model.exception.BaseException;

public class RedundancyDataItem extends BaseException {
    public RedundancyDataItem() {
        super();
    }

    public RedundancyDataItem(String message, Throwable cause) {
        super(message, cause);
    }

    public RedundancyDataItem(String message) {
        super(message);
    }

    public RedundancyDataItem(Throwable cause) {
        super(cause);
    }
}

