package RequestResult;

import model.Person;


/**
 * Result containing info to send back to handler whether or not service is successful
 */

public class PersonResult {

    //array of person objects called data

    /**
     * Array of person objects to hold person after person request is called
     */

    Person[] data;

    /**
     * bollean to determine if person request was successful or not
     */

    Boolean success;

    //error, will also use success

    /**
     * String to hold message if person request encountered an error
     */

    String message;

}