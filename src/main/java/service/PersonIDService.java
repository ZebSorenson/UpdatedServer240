package service;

import RequestResult.PersonIDResult;
import RequestResult.PersonResult;
import dao.AuthtokenDao;
import dao.DataAccessException;
import dao.Database;
import dao.PersonDao;
import model.Authtoken;
import model.Person;

import java.sql.Connection;

/**
 * Returns the single Person object with the specified ID
 */

public class PersonIDService {

    /**
     * Returns the single Person object with the specified ID (if the person is associated with the current user). The current user is determined by the provided authtoken.
     * @param personIDString the ID to identify the person to perform function on
     * @return PersonResult object containing info on whether or not the service was successful
     */

    public PersonIDResult RetrievePersonID(String personIDString, String authToken) throws DataAccessException {

        System.out.println("you have arrived in the PersonID service");
        System.out.println("The personID given is "+ personIDString);

        //just need to use the dao to get the person object with this ID, verify that it belongs to the user with the
        //given authtoken and fill in the result object

        //HOW DO WE DEAL WITH THE AUTHTOKEN??

        //use your finds to find the username connect to the authtoken and the personID string and then double
        //check to make sure they are conneceted to the same things.

        //the authtoken is not connected to the user that is the associated username of the person

        Database db = new Database();
        Connection conn = db.getConnection();

        try{

            PersonDao personDataAccess = new PersonDao(conn);

            Person personToFind = personDataAccess.find(personIDString);

            AuthtokenDao authTokenDataAccess = new AuthtokenDao(conn);
            Authtoken myAuthToken = authTokenDataAccess.findAuthToken(authToken);

            if(personToFind==null){
                PersonIDResult result = new PersonIDResult();
                result.setMessage("Error: Person doesn't exist");
                result.setSuccess(false);
                db.closeConnection(false);
                return result;
            }

            if(myAuthToken==null){
                PersonIDResult result = new PersonIDResult();
                result.setMessage("Error: bad authtoken");
                result.setSuccess(false);
                db.closeConnection(false);
                return result;
            }

            if(personToFind.getAssociatedUsername().equals(myAuthToken.getUsername())){

                PersonIDResult result = new PersonIDResult();
                result.setAssociatedUsername(personToFind.getAssociatedUsername());
                result.setPersonID(personIDString);
                result.setFirstName(personToFind.getFirstName());
                result.setLastName(personToFind.getLastName());
                result.setGender(personToFind.getGender());
                result.setFatherID(personToFind.getFatherID());
                result.setMotherID(personToFind.getMotherID());
                result.setSpouseID(personToFind.getSpouseID());
                result.setSuccess(true);
                db.closeConnection(true);
                return result;


            }else{
                PersonIDResult result = new PersonIDResult();
                result.setMessage("Error: Invalid user or AuthToken");
                result.setSuccess(false);
                db.closeConnection(false);
                return result;

            }

        } catch (DataAccessException e) {

            PersonIDResult result = new PersonIDResult();
            result.setMessage("Error: Data access exception caught");
            result.setSuccess(false);
            db.closeConnection(false);

            return result;

        }

    }


}

//    Returns the single Person object with the specified ID
//        (if the person is associated with the current user). The current user is determined by the provided authtoken.