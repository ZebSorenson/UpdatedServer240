package service;

import RequestResult.ClearRequest;
import RequestResult.ClearResult;
import dao.*;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Service object that will perform the action of deleting all data from the database, including user, authtoken, person, and event data
 */
public class ClearService {

    Database db = new Database();



    /**
     * clears ALL data from database
     *  the request object containing the needed info to perform the requested service. This is coming from the http handler
     * @return clearResult object containing info depending on whether or not the service was successful
     */

    public ClearResult clear() {

        System.out.println("you have arrived at the clear SERVICE");

        ClearResult resultToreturn = new ClearResult();
    try{

        Connection conn = db.getConnection();



        //is this bad to have so many connections getting passed around?
        AuthtokenDao dataAccessAUTHDao = new AuthtokenDao(conn);
        EventDao dataAccessEventDao = new EventDao(conn);
        PersonDao personDataAccessDao = new PersonDao(conn);
        UserDao userDataAccessDao = new UserDao(conn);

        dataAccessAUTHDao.clear();
        dataAccessEventDao.clear();
        personDataAccessDao.clear();
        userDataAccessDao.clear();
        resultToreturn.setSuccess(true);
        db.closeConnection(true);
        resultToreturn.setMessage("Clear succeeded");
        resultToreturn.setSuccess(true);
        System.out.println("you have arrived at the end of the try block");

    } catch (DataAccessException e) {
        System.out.println("Error encountered in the clear service when attempting to clear");
        resultToreturn.setSuccess(false);
        resultToreturn.setMessage("DataAccessException when attempting to clear the database");
        db.closeConnection(false);

        throw new RuntimeException(e);
    }

        return resultToreturn;
    }


}

//want to pass request objects