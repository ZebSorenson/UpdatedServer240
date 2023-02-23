package service;

import RequestResult.eventIDResult;
import model.Authtoken;
import model.Event;

/**
 * Gets the single event object in the result if it successfully performs the service
 */

public class eventID {

    /**
     * Will return specific single event based on the specified ID
     * @param token used to determine which user to perform function with
     * @return the even determined by function use
     */

    public eventIDResult eventID(Authtoken token){

        return null;

    }
}

//Returns the single Event object with the specified ID (if the event is associated with the current user). The current user is determined by the provided authtoken.