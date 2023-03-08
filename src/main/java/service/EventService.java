package service;

import RequestResult.EventResult;
import model.Authtoken;

/**
 * Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.
 */

public class EventService {

    /**
     * will perform the service of returning all family members of the current user
     * @param tokenString token used to determine User to return events for.
     * @return an eventResult object containing an array called data which is a list of all the events as well as a true boolean or an error message with false set
     */

    public EventResult GetAllEvents(String tokenString){

        return null;
    }
}

//Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.