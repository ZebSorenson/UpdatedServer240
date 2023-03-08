package RequestResult;

import model.Event;


/**
 * Result containing info to send back to handler whether or not service is successful
 */

public class EventResult {

    //array of json events called data

    /**
     * array to hold list of events
     */

    Event[] data;

    /**
     * used to determine if the request was a success or not. Yes or no result
     */

    Boolean success;

    /**
     * Used for message displayed as error
     */

    String message; //only for error

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}