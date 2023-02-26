package RequestResult;

/**
 * Result containing info to send back to handler whether or service is successful
 */
public class RegisterResult {

    //Success Response body

    /**
     * Auth token generated after succesful register request
     */

    String authtoken;

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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