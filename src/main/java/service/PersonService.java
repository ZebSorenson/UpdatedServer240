package service;

import RequestResult.PersonResult;
import model.Authtoken;
import model.Person;

/**
 * Returns ALL family members of the current user
 */

public class PersonService {

    /**
     * Returns ALL family members of the current user. The current user is determined by the provided authtoken.
     * @param token used to determine which user to return all persons related to the user with this auth token
     * @return PersonResult object containing info on whether or not the service was successful
     */

    public PersonResult person(Authtoken token){
        return null;
    }

}


//Returns ALL family members of the current user. The current user is determined by the provided authtoken.