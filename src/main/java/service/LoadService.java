package service;

import RequestResult.LoadRequest;
import RequestResult.LoadResult;
import dao.*;

import java.sql.Connection;
import java.sql.SQLException;

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

        try{





            //is this bad to have so many connections getting passed around?
            AuthtokenDao dataAccessAUTHDao = new AuthtokenDao(conn);
            EventDao dataAccessEventDao = new EventDao(conn);
            PersonDao personDataAccessDao = new PersonDao(conn);
            UserDao userDataAccessDao = new UserDao(conn);

            dataAccessAUTHDao.clear();
            dataAccessEventDao.clear();
            personDataAccessDao.clear();
            userDataAccessDao.clear();
            result.setSuccess(true);
            conn.commit();
            conn.close();
            result.setMessage("Clear succeeded");
            result.setSuccess(true);
            System.out.println("you have arrived at the end of the try block");

        } catch (SQLException e) {
            result.setMessage("A SQL exception was caught while attempting to clear");
            result.setSuccess(false);
            conn.rollback();
            conn.close();


            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            result.setMessage("A DataAccess exception was caught while attempting to clear");
            result.setSuccess(false);
            conn.rollback();
            conn.close();
            throw new RuntimeException(e);
        }


        return result;

    }

}



//    Clears all data from the database (just like the /clear API)
//    Loads the user, person, and event data from the request body into the database.
