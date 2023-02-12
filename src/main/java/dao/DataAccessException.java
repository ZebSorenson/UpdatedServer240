package dao;

/**
 * Exception for error handling when attempting to access data
 */
public class DataAccessException extends Exception {
    /**
     * Thrown when error is encountered when accessing the databse.
     * @param message relating to the error
     */
    DataAccessException(String message) {
        super(message);
    }
}