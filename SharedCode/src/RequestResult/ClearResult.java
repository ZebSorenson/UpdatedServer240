package RequestResult;


/**
 * Result containing info to send back to handler whether or not service is successful
 */

public class ClearResult {

    /**
     * Message displayed post clear
     */

    String message;

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

    /**
     * set to true or false based on result of clear
     */

    Boolean success;

    //error will use the same items.
}