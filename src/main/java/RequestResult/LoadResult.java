package RequestResult;

/**
 * Object to hold results of load Request in a response object
 */

public class LoadResult {

    /**
     * String to hold message if error is encountered
     */
    String message;

    /**
     * Boolean to determine if request was successful or not. Yes or no value
     */

    Boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    //Same will be used in a failure
}