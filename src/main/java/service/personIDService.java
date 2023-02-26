package service;

import RequestResult.PersonResult;
import model.Authtoken;
import model.Person;

/**
 * Returns the single Person object with the specified ID
 */

public class personIDService {

    /**
     * Returns the single Person object with the specified ID (if the person is associated with the current user). The current user is determined by the provided authtoken.
     * @param token the ID to identify the person to perform function on
     * @return PersonResult object containing info on whether or not the service was successful
     */

    public PersonResult personID(Authtoken token){
        return null;
    }


}

//    Returns the single Person object with the specified ID
//        (if the person is associated with the current user). The current user is determined by the provided authtoken.