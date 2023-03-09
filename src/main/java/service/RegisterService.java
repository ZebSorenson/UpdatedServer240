package service;

import RequestResult.RegisterRequest;
import RequestResult.RegisterResult;
import dao.*;
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

        Connection conn = db.getConnection();

        UserDao userDataAccess = new UserDao(conn);

        if(userDataAccess.findUsername(regReq.getUsername())!=null){

            db.closeConnection(false);
            RegisterResult returnResult = new RegisterResult();
            returnResult.setMessage("Error trying to register the user");
            returnResult.setSuccess(false);
            return returnResult;

        }



        User registerUser = createUser(regReq);



        try{



            PersonDao dataAccessPerson = new PersonDao(db.getConnection());

            userDataAccess.insert(registerUser); //insert the new user into the database

            // insert the new authToken for the user into the database

            String uniqueTokenString = UUID. randomUUID().toString();

            RegisterResult returnResult = new RegisterResult();

            //create the return object

            returnResult.setAuthtoken(uniqueTokenString);

            Authtoken newAuthTokenObject = new Authtoken(uniqueTokenString, registerUser.getUsername());

            AuthtokenDao authTokenDataAccess = new AuthtokenDao(db.getConnection());

            authTokenDataAccess.insert(newAuthTokenObject);

            System.out.println("The authToken to test with is...DELETE THIS CODE..."+uniqueTokenString); //DELETE ME!!!

            returnResult.setUsername(registerUser.getUsername());

            returnResult.setPersonID(registerUser.getPersonID());

            returnResult.setSuccess(true);

            TreeGenerator registerTree = new TreeGenerator(conn, returnResult.getUsername()); // creating our tree generator
            //giving our tree generator our connection to the database and the username for which we're creating the tree\


            Person userPerson = registerTree.generatePersonTree(regReq.getGender(), 4, Year.now().getValue());
            //TREE CODE ABOVE

            db.closeConnection(true); //PUT ME BACK!!

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