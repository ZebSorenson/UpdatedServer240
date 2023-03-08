package service;

import RequestResult.FillResult;
import dao.*;
import model.User;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * service object that will populate the server's database with data for the given username.
 * Default will be 4 generations but can call fill with int param to specify how many generations the user would like
 */
public class FillService {


    Connection conn = null;
    String userName = null;

    final int currYear = 2023;

    public FillService(String userName){
        this.userName = userName;
    }

    /**
     * will add data for the username passed in
     * @param username specificed username for which to add data for
     * This fill function will default to 4 generations as a specific is not given
     * @return fillResult objectt containing info on whether or not service was successful
     */

    public FillResult fill(String username){
        System.out.println("you have arrived at just the single username service"); //WILL ACTUALLY NOT NEED THIS FUNCTION.
        //EVERY CALL TO THE SERVICE WILL PROVIDE A NUMBER OF GENERATIONS. CAN PROBABLY DELETE THIS LATER
    return null;
    }

    /**
     * Will perform same function as first fill but with added option to place integer to identify how many generations of ancestors to be added
     * @param username username of user to have data added to specify the number of generations of ancestors to be generated,
     * @param generations specific number of generations of persons for the given username
     * @return fillResult object containing info on whether or not service was succesful
     */

    public FillResult fill(String username, Integer generations) throws DataAccessException, SQLException, FileNotFoundException {


        FillResult result = new FillResult();

        //put this into a Try block
        Database db = new Database();

        try {


            conn = db.getConnection();

            UserDao userDataAccess = new UserDao(conn);
            EventDao eventDataAccess = new EventDao(conn);
            PersonDao personDataAccess = new PersonDao(conn);

            User basePerson = userDataAccess.findUser(userName);

            if (userDataAccess.findUsername(userName) == null) {
                //return a null user
                result.setSuccess(false);
                result.setMesssage("User does not exist in the database");
                db.closeConnection(false);
                return result;
            } else if (userDataAccess.findUsername(userName) != null) {

                personDataAccess.deleteUserData(userName);

                eventDataAccess.deleteUserData(userName);

                TreeGenerator newPersonTree = new TreeGenerator(conn, userName);

                newPersonTree.generatePersonTree(basePerson.getGender(), generations, currYear);

                result.setMesssage("Successfully");

                result.setSuccess(true);

                db.closeConnection(true);
            }

        } catch (DataAccessException e) {
            result.setMesssage("DataAccess Exception caught");
            result.setSuccess(false);
            db.closeConnection(false);
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            result.setMesssage("FileNotFoundException Exception caught");
            result.setSuccess(false);
            db.closeConnection(false);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            result.setMesssage("SQLException Exception caught");
            result.setSuccess(false);
            db.closeConnection(false);
            throw new RuntimeException(e);
        }
        //if not null, you can just call the info and clear everything.



        return result;

    }

    //just pass in a fill request objects

    //will return the proper result object related to the service



}



//from the specs
//    Populates the server's database with generated data for the specified username.
//        The required "username" parameter must be a user already registered with the server.
//        If there is any data in the database already associated with the given username, it is deleted.
//        The optional "generations" parameter lets the caller specify the number of generations of ancestors to be generated, and must be a non-negative integer
//        (the default is 4, which results in 31 new persons each with associated events).
//        More details can be found in the earlier section titled “Family History Information
