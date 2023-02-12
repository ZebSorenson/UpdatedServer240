package model;

/**
 * User object to hold data of new user
 */
public class User {

    /**
     * String to hold data of username of new User object
     */

    private String username;

    /**
     * String to hold data of password of new User object
     */

    private String password;

    /**
     * String to hold data of email of new User object
     */

    private String email;

    /**
     * String to hold data of first name of new User object
     */

    private String firstName;

    /**
     * String to hold data of last name of new User object
     */


    private String lastName;

    /**
     * String to hold data of gender of new User object
     */

    private String gender;

    /**
     * String to hold data of unique ID of new User object
     */

    private String personID;

    /**
     * Constructor to set data members of new User object. Please reference the documentation for data members of class.
     * Parameters reflect class data members to be set to this person object
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     * @param personID
     */

    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}