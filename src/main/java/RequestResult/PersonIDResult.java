package RequestResult;


/**
 * Result containing info to send back to handler whether or not service is successful
 */

public class PersonIDResult {

    /**
     * String to hold associatedUsername after personID request
     */

    String associatedUsername;

    /**
     * String to hold personID after personID request
     */

    String personID;

    /**
     * String to hold firstName after personID request
     */


    String firstName;

    /**
     * String to hold lastName after personID request
     */


    String lastName;

    /**
     * String to hold gender after personID request
     */


    String gender;

    /**
     * String to hold fatherID after personID request
     */

    String fatherID;

    /**
     * String to hold motherID after personID request
     */

    String motherID;

    /**
     * String to hold spouseID after personID request
     */

    String spouseID;

    /**
     * Boolean to determine if personID request was succesful or not
     */


    String success;

    //error, will also use success

    /**
     * String to hold message if error was encountered in PersonID request
     */


    String message;


    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
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

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}