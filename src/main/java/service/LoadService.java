package service;

import RequestResult.LoadRequest;
import RequestResult.LoadResult;
import dao.*;
import model.Authtoken;
import model.Event;
import model.Person;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Clears all data from the database (just like the /clear API)
 * Loads the user, person, and event data from the request body into the database.
 */

public class LoadService {

    /**
     * Loads the user, person, and event data from the request body into the database.
     * @param loadReq the request object containing info for the service to perform function on
     * @return LoadResult object containing info on whether on not the service was successful
     */

    public LoadResult load(LoadRequest loadReq) throws DataAccessException, SQLException {

        System.out.println("You have arrived at the load Service function");
        System.out.println("You still need to handle inputing the JSON data to the DB!!!!");

        Database db = new Database();

        LoadResult result = new LoadResult();
        Connection conn = db.getConnection();

        int numUsers = 0;

        int numPeople =0;

        int numEvents = 0;

        try{

            System.out.println("Entering the try block");


            //is this bad to have so many connections getting passed around?
            AuthtokenDao dataAccessAUTHDao = new AuthtokenDao(conn);
            EventDao dataAccessEventDao = new EventDao(conn);
            PersonDao personDataAccessDao = new PersonDao(conn);
            UserDao userDataAccessDao = new UserDao(conn);

            dataAccessAUTHDao.clear();
            dataAccessEventDao.clear();
            personDataAccessDao.clear();
            userDataAccessDao.clear();


            for(User user: loadReq.getUsers() ){
                userDataAccessDao.insert(user);
                Authtoken authtoken = new Authtoken(UUID.randomUUID().toString(), user.getUsername());
                dataAccessAUTHDao.insert(authtoken);
                numUsers++;

            }

            for(Person person: loadReq.getPersons()){
                personDataAccessDao.insert(person);
                numPeople++;
            }

            for(Event event: loadReq.getEvents()){
                dataAccessEventDao.insert(event);
                numEvents++;
            }



            result.setSuccess(true);
            result.setMessage("Successfully added " + numUsers+ " users, "+ numPeople+" persons, and " + numEvents+" events to the database.");
            System.out.println("you have arrived at the end of the try block");
           db.closeConnection(true);

        }  catch (DataAccessException e) {
            result.setMessage("A DataAccess exception was caught while attempting to clear");
            result.setSuccess(false);
            db.closeConnection(false);
            e.printStackTrace();
            return result;

        }


        return result;

    }

}



//    Clears all data from the database (just like the /clear API)
//    Loads the user, person, and event data from the request body into the database.
