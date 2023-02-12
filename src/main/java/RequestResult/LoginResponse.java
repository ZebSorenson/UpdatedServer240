package RequestResult;

/**
 * Result object to be received after login request
 */

public class LoginResponse {

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