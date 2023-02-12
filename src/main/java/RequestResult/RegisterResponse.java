package RequestResult;

/**
 * Response of register request object
 */
public class RegisterResponse {

    //Success Response body

    /**
     * Auth token generated after succesful register request
     */

    String authtoken;

    /**
     * Auth username generated after succesfful register request
     */


    String username;

    /**
     * Auth personID generated after succesfful register request
     */

    String personID;

    /**
     * Boolean used to determine if register request was successful or not
     */

    Boolean success; //this can also be updated with a failure

    //Error response body

    /**
     * String to hold message if request was not successful
     */

    String message;






}