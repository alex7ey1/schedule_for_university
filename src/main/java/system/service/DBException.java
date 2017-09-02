package system.service;

/**
 * Created by cezar on 4/4/17.
 */
public class DBException extends Exception {
    public DBException() {
        super();
    }

    public DBException(Throwable throwable) {
        super(throwable);
    }

    public DBException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
