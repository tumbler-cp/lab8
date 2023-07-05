package comm;

import java.io.Serializable;

public enum Signal implements Serializable {
    COMMAND,
    TEXT,
    FILE,
    LOGIN,
    ACCEPT,
    DECLINE,
    REG,
    TEMP_CONN,
    ALLOC,
    TABLE,
    CLOSING,
    EXIT
}
