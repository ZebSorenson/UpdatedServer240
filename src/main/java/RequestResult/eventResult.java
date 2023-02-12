package RequestResult;

import model.Event;

/**
 * Returned as result of and event request
 */

public class eventResult {

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
}