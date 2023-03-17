package model;

/**
 * Authtoken object to hold data
 */
public class Authtoken {

    /**
     * String to hold the unique ID generated
     */
    private String authtoken;
    /**
     * Username String in connection with the new authtoken
     */
    private String username;

    /**
     * Constructor for new authtoken.
     * @param authtoken new authtoken to be added to this authtoken
     * @param username username to be associated with this authtoken
     */

    public Authtoken(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
    }

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
}