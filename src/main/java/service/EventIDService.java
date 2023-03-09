package service;

import RequestResult.EventIDResult;
import RequestResult.PersonIDResult;
import dao.*;
import model.Authtoken;
import model.Event;
import model.Person;

import java.sql.Connection;

/**
 * Returns the single Event object with the specified ID (if the event is associated with the current user). The current user is determined by the provided authtoken
 */

public class EventIDService {

    /**
     * Will return specific single event based on the specified ID
     * @param eventIDString used to determine which user to perform function with
     * @return an eventIDResult object containing the event data if the service is successful and error info if not
     */

    public EventIDResult GetSingleEventID(String eventIDString, String authToken){

        System.out.println("You have entered the eventID service function");

        //MAKE SURE TO CHECK THE AUTH TOKEN!!!

        try{
            Database db = new Database();
            Connection conn = db.getConnection();
            EventDao eventDataAccess = new EventDao(conn);

            Event eventToFind = eventDataAccess.find(eventIDString);

            AuthtokenDao authDataAccess = new AuthtokenDao(conn);

            if(!authDataAccess.isValidAuth(eventToFind.getAssociatedUsername(),authToken) || eventToFind==null ){
                EventIDResult result = new EventIDResult();
                result.setSuccess(false);
                result.setMessage("Invalid authToken");
                db.closeConnection(false);
                return result;
            }



            else if(eventToFind!=null){

                EventIDResult result = new EventIDResult();

                result.setAssociatedUsername(eventToFind.getAssociatedUsername());
                result.setEventID(eventToFind.getEventID());
                result.setPersonID(eventToFind.getPersonID());
                result.setLatitude(eventToFind.getLatitude());
                result.setLongitude(eventToFind.getLongitude());
                result.setCountry(eventToFind.getCountry());
                result.setCity(eventToFind.getCity());
                result.setEventType(eventToFind.getEventType());
                result.setYear(eventToFind.getYear());
                result.setSuccess(true);
                db.closeConnection(true);
                return result;

            }

        } catch (DataAccessException e) {

            EventIDResult result = new EventIDResult();

            result.setMessage("Data access exception caught");
            result.setSuccess(false);

            return result;

        }



        return null;

    }


    //end of class
}

//Returns the single Event object with the specified ID (if the event is associated with the current user). The current user is determined by the provided authtoken.