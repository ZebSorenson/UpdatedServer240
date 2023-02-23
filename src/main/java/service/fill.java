package service;

/**
 * service object that will populate the server's database with data for the username
 */
public class fill {

    /**
     * will add data for the username passed in
     * @param username specificed username for which to add data for
     */

    public void fill(String username){

    }

    /**
     * Will perform same function as first constructor but with added option to place integer to identify how many generations of ancestors to be added
     * @param username username of user to have data added to specify the number of generations of ancestors to be generated,
     * @param generations
     */

    public void fill(String username, Integer generations){

    }

    //just pass in a fill request objects

    //will return the proper result object related to the service



}



//from the specs
//    Populates the server's database with generated data for the specified username.
//        The required "username" parameter must be a user already registered with the server.
//        If there is any data in the database already associated with the given username, it is deleted.
//        The optional "generations" parameter lets the caller specify the number of generations of ancestors to be generated, and must be a non-negative integer
//        (the default is 4, which results in 31 new persons each with associated events).
//        More details can be found in the earlier section titled “Family History Information
