package RequestResult;

import model.Person;

/**
 * object to hold results after person request is called
 */

public class personResult {

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