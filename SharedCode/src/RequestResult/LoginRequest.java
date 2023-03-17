package RequestResult;

/**
 * Request object sent to  Login service to act on
 */
public class LoginRequest {

    /**
     * String for username to be sent in the request
     */

    public String username;

    /**
     * String for password to be sent in the request
     */

    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

//should make them private and then have getters and setters