package service;

import RequestResult.LoginRequest;
import RequestResult.LoginResult;
import dao.DataAccessException;
import dao.Database;
import dao.UserDao;
import model.Authtoken;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

/**
 * service object that will log the user in
 */

public class LoginService {

    /**
     * Database for us to establish a connection with
     */

    Database db = new Database();

    Connection loginConnection = null;

//    public LoginService(Connection conn) {
//        this.loginConnection =conn;
//    }


//create database object here!!!
    //using the databse you'll create DAOs to interact with the objectsa
    //this is because thhe constructor of the DAO takes a connection object. So create instance here.
    //DAO can't do anything with DB connection.



    /**
     *
     * @param loginReq request object containing info for the service to perform function on
     * @return loginResult object containing info on whether or not the service was succesfull
     * @throws DataAccessException
     */


    public LoginResult login(LoginRequest loginReq) throws DataAccessException, SQLException {

        //may need to handle something that is not formated correctly
        //am I handling the connection correctly??? With commits and rollbacks?

        Connection conn = db.getConnection();

        LoginResult returnResult = new LoginResult();

        UserDao loginDataAccess = new UserDao(conn);


        String userName = loginReq.getUsername();
        String passWord = loginReq.getPassword();

        boolean successLogin = false;

        if(loginDataAccess.findUsername(userName)!=null){
            if(loginDataAccess.verifyCredentials(userName, passWord)){
                UUID myUUID = UUID.randomUUID();
                String newAuthToken = myUUID.toString();
                //the username and password match!
                System.out.println("They should match! If you've made it this far, things are going well");
                System.out.println(userName+" "+passWord);
//                LoginResult returnResult = new LoginResult();
                returnResult.setAuthtoken(newAuthToken);
                returnResult.setUsername(userName);
                returnResult.setPersonID(loginDataAccess.findPersonID(userName));
                returnResult.setSuccess(true);
                conn.commit();
                return returnResult;
            }
            //check to see if the password is incorrect

            else if(!loginDataAccess.verifyCredentials(userName, passWord)){

                returnResult.setSuccess(false);
                returnResult.setMessage("Error: incorrect password");
                conn.rollback();
                return returnResult;


            }
        }else if(loginDataAccess.find(userName)==null){
            returnResult.setSuccess(false);
            returnResult.setMessage("Error: invalid username or password");
            conn.rollback();
            return returnResult;
        }



        //pass that into a find function in the DAO

        //want to check that our database has a user with the username and password that is contained in the request object

        //how do we get the username and password out of this loginrequst?


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