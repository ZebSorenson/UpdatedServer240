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

    Connection conn = null;

    /**
     * clears ALL data from database
     *  the request object containing the needed info to perform the requested service. This is coming from the http handler
     * @return clearResult object containing info depending on whether or not the service was successful
     */

    public ClearResult clear(){

        ClearResult resultToreturn = new ClearResult();
        //is this bad to have so many connections getting passed around?
        AuthtokenDao dataAccessAUTHDao = new AuthtokenDao(conn);
        EventDao dataAccessEventDao = new EventDao(conn);
        PersonDao personDataAccessDao = new PersonDao(conn);
        UserDao userDataAccessDao = new UserDao(conn);

    try{
        dataAccessAUTHDao.clear();
        dataAccessEventDao.clear();
        personDataAccessDao.clear();
        userDataAccessDao.clear();
        resultToreturn.setSuccess(true);
        conn.commit();
        conn.close();
        resultToreturn.setMessage("Clear succeeded");

    } catch (DataAccessException e) {
        System.out.println("Error encountered in the clear service when attempting to clear");
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

        return resultToreturn;
    }


}

//want to pass request objects