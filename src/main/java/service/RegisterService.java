package service;

import RequestResult.RegisterRequest;
import RequestResult.RegisterResult;
import dao.DataAccessException;
import dao.Database;
import dao.PersonDao;
import dao.UserDao;
import model.Authtoken;
import model.Person;
import model.User;

import java.sql.Connection;
import java.time.Year;
import java.util.UUID;

/**
 * Creates a new user account (user row in the database)
 */

public class RegisterService {

    /**
     * Database for us to establish a connection with
     */

    Database db = new Database();

    //do I need to place this dabatase in the functions?

    //from the specs
    //    Creates a new user account (user row in the database)
    //    Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if called with a generations value of 4 and this new user’s username as parameters)
    //    Logs the user in
    //    Returns an authtoken

    /**
     *
     * @param regReq request object constaining info for the service to perform function with
     * @return RegisterResult object containing info on whether or not the service was succesfull
     * @throws DataAccessException
     */



    public RegisterResult register(RegisterRequest regReq) throws DataAccessException {

        System.out.println("You have arrived at the register SERVICE function");

        User registerUser = createUser(regReq);



        Connection conn = db.getConnection();

        try{

            UserDao userDataAccess = new UserDao(conn);
            PersonDao dataAccessPerson = new PersonDao(db.getConnection());
            userDataAccess.clear(); //DELETE ME LATER DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE DELETE
            dataAccessPerson.clear(); //DELETE LATER
            userDataAccess.insert(registerUser);

            String newAuthToken = UUID. randomUUID().toString();

            RegisterResult returnResult = new RegisterResult();

            //create the return object

            returnResult.setAuthtoken(newAuthToken);

            returnResult.setUsername(registerUser.getUsername());

            returnResult.setPersonID(registerUser.getPersonID());

            returnResult.setSuccess(true);

            TreeGenerator registerTree = new TreeGenerator(conn, returnResult.getUsername()); // creating our tree generator
            //giving our tree generator our connection to the database and the username for which we're creating the tree\


            Person userPerson = registerTree.generatePersonTree(regReq.getGender(), 2, Year.now().getValue());
            //set the rest of the atributes of the person right here before inserting!!!!! Need a complete person!!!
            // dataAccessPerson.insert(userPerson);

            db.closeConnection(true);

            return returnResult;


        } catch (DataAccessException e) {
            System.out.println("An error occurred in the Register service class in the register function ");
            db.closeConnection(false);
            RegisterResult returnResult = new RegisterResult();
            returnResult.setMessage("Error trying to register the user");
            returnResult.setSuccess(false);
            throw new RuntimeException(e);
        } catch(Exception ex){

            ex.printStackTrace();
            db.closeConnection(false);
            RegisterResult returnResult = new RegisterResult();
            returnResult.setMessage("Error trying to register the user");
            returnResult.setSuccess(false);
        }
    return null;
    }


    private User createUser(RegisterRequest regReq){

        String newPersonID = UUID.randomUUID().toString();

        //create a new

        User registerUser = new User(regReq.getUsername(), regReq.getPassword(), regReq.getEmail(), regReq.getFirstName(), regReq.getLastName(), regReq.getGender(), newPersonID);

        return  registerUser;

    }




}