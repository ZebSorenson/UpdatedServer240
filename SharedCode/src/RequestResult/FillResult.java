package RequestResult;


/**
 * Result containing info to send back to handler whether or not service is successful
 */

public class FillResult {

    /**
     * Message to be diplayed if error is recieved in result
     */

    String message;

    /**
     * Used to determine if request was successful or not
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

    //same will be the same with error
}