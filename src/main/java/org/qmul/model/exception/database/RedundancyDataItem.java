package org.qmul.model.exception.database;

import org.qmul.model.exception.BaseException;

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

