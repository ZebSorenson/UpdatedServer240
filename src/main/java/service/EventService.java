package service;

import RequestResult.EventResult;
import RequestResult.PersonResult;
import dao.*;
import model.Authtoken;

import java.sql.Connection;

/**
 * Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.
 */

public class EventService {

    /**
     * will perform the service of returning all family members of the current user
     * @param tokenString token used to determine User to return events for.
     * @return an eventResult object containing an array called data which is a list of all the events as well as a true boolean or an error message with false set
     */

    public EventResult GetAllEvents(String tokenString) throws DataAccessException {

        Database db = new Database();

        Connection conn = db.getConnection();


        try {
            AuthtokenDao authTokenDataAccess = new AuthtokenDao(conn);

            Authtoken myAuth = authTokenDataAccess.findAuthToken(tokenString);

            EventDao eventDataAccess = new EventDao(conn);

            if (myAuth == null) {
                db.closeConnection(false);
                EventResult result = new EventResult();
                result.setSuccess(false);
                result.setMessage("Error while looking for authtoken");
                return result;
            } else {
                EventResult result = new EventResult();
                result.setData(eventDataAccess.getAllEventsForUsername(myAuth.getUsername()));
                result.setSuccess(true);
                db.closeConnection(true);
                return result;
            }


        } finally {

        }


    }


    }




    //end of class


//Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.