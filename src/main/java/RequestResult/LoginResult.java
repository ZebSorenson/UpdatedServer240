package RequestResult;

/**
 * Result object to be received after login request
 */

public class LoginResult {
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
     *  String to hold authtoken after login request
     */

    String authtoken;

    /**
     *  String to hold username after login request
     */

    String username;

    /**
     *  String to hold personID after login request
     */

    String personID;

    /**
     *  Boolean represent of login request was successful or not
     */

    Boolean success; // can also be used in error

    //error

    /**
     *  Message to be held if error is encountered
     */

    String message;


}