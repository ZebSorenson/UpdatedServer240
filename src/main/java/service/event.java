package service;

import model.Authtoken;
import model.Event;

/**
 * Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.
 */

public class event {

    /**
     * based on the given auth token, constructor will return  all family members of user based on auth token
     * @param token token used to determine user on which the function will be called
     * @return array of all events for all family members
     */

    public Event[] event(Authtoken token){
        return null;
    }
}

//Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.