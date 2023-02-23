package service;

import RequestResult.LoginRequest;
import RequestResult.LoginResult;
import model.Authtoken;

/**
 * service object that will log the user in
 */

public class LoginService {

    //create database object here!!!
    //using the databse you'll create DAOs to interact with the objectsa
    //this is because thhe constructor of the DAO takes a connection object. So create instance here.
    //DAO can't do anything with DB connection.

    /**
     * Logs in the user
     * @param username specified username of user to be logged in
     * @return AuthToken unique ID for user that was logged in
     */
    public Authtoken login(String username){
        return null;
    }

    public LoginResult login(LoginRequest login_request_object){

        return null;
    }

//    from the specs
//    Logs the user in
//    Returns an authtoken.


    //this part will interact with the dao classes. Call these methods on them
    //helper function that can validade the login rquest and get a user out of it.
    //get the username and password from the request...go to the databse and look to see if they match
    //this will determine that the user is validated...CREATE AN AUTH TOKEN HERE...return the auth token in the result object
    //this is what you will give back to the handler
    //could manually create user in the same create table statements do an insert user with the DB browser tool.

    //create database object here!!!


}