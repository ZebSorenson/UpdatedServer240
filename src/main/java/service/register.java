package service;

import model.Authtoken;

/**
 * Creates a new user account (user row in the database)
 */

public class register {

    //from the specs
    //    Creates a new user account (user row in the database)
    //    Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if called with a generations value of 4 and this new user’s username as parameters)
    //    Logs the user in
    //    Returns an authtoken

    /**
     * Creates new user with the username passed in as a paramter
     * @param username string to use the username of the new user object
     * @return AuthToken unique auth token for the user when they are registered
     */

    public Authtoken  register(String username){

        return null;
    }


}