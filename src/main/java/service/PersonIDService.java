package service;

import RequestResult.PersonIDResult;
import RequestResult.PersonResult;
import model.Authtoken;

/**
 * Returns the single Person object with the specified ID
 */

public class PersonIDService {

    /**
     * Returns the single Person object with the specified ID (if the person is associated with the current user). The current user is determined by the provided authtoken.
     * @param personIDString the ID to identify the person to perform function on
     * @return PersonResult object containing info on whether or not the service was successful
     */

    public PersonIDResult RetrievePersonID(String personIDString){

        System.out.println("you have arrived in the PersonID service");

        return null;
    }


}

//    Returns the single Person object with the specified ID
//        (if the person is associated with the current user). The current user is determined by the provided authtoken.