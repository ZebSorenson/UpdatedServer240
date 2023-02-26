package RequestResult;


/**
 * Result containing info to send back to handler whether or not service is successful
 */

public class FillResult {

    /**
     * Message to be diplayed if error is recieved in result
     */

    String messsage;

    /**
     * Used to determine if request was successful or not
     */

    Boolean success;

    //same will be the same with error
}