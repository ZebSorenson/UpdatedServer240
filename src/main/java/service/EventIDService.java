package service;

import RequestResult.EventIDResult;
import model.Authtoken;

/**
 * Returns the single Event object with the specified ID (if the event is associated with the current user). The current user is determined by the provided authtoken
 */

public class EventIDService {

    /**
     * Will return specific single event based on the specified ID
     * @param token used to determine which user to perform function with
     * @return an eventIDResult object containing the event data if the service is successful and error info if not
     */

    public EventIDResult eventID(Authtoken token){

        return null;

    }
}

//Returns the single Event object with the specified ID (if the event is associated with the current user). The current user is determined by the provided authtoken.