package RequestResult;

/**
 * request object for when register service is called
 */

public class RegisterRequest {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * String to hold username for request object
     */

    String username;

    /**
     * String to hold password for request object
     */

    String password;

    /**
     * String to hold email for request object
     */

    String email;

    /**
     * String to hold firstName for request object
     */

    String firstName;

    /**
     * String to hold lastName for request object
     */


    String lastName;

    /**
     * String to hold gender for request object
     */

    String gender;





}