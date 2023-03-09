package service;

import RequestResult.PersonResult;
import dao.AuthtokenDao;
import dao.DataAccessException;
import dao.Database;
import dao.PersonDao;
import model.Authtoken;
import model.Person;

import java.sql.Connection;

/**
 * Returns ALL family members of the current user
 */

public class PersonService {

    /**
     * Returns ALL family members of the current user. The current user is determined by the provided authtoken.
     * @param token used to determine which user to return all persons related to the user with this auth token
     * @return PersonResult object containing info on whether or not the service was successful
     */

    public PersonResult GetAllPersons(String token) throws DataAccessException {

        Database db = new Database();

        Connection conn = db.getConnection();

        //want to check that this auth token is connected to the username that is the person's associated username


        try {
            AuthtokenDao authTokenDataAccess = new AuthtokenDao(conn);

            Authtoken myAuth = authTokenDataAccess.findAuthToken(token);

            PersonDao personDataAccess = new PersonDao(conn);

            if (myAuth == null) {
                db.closeConnection(false);
                PersonResult result = new PersonResult();
                result.setSuccess(false);
                result.setMessage("Error while looking for authtoken");
                return result;
            }

            else {
                PersonResult result = new PersonResult();
                result.setData(personDataAccess.getAllPeopleWithUsername(myAuth.getUsername()));
                result.setSuccess(true);
                db.closeConnection(true);
                return result;
            }


        } catch(DataAccessException e){
            {
                db.closeConnection(false);
                PersonResult result = new PersonResult();
                result.setSuccess(false);
                result.setMessage("Error while looking for persons");
                return result;
            }
        }


    }



    //end of class
}


//Returns ALL family members of the current user. The current user is determined by the provided authtoken.