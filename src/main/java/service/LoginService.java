package service;

import RequestResult.LoginRequest;
import RequestResult.LoginResult;
import dao.AuthtokenDao;
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



        LoginResult returnResult = new LoginResult();




        String userName = loginReq.getUsername();
        String passWord = loginReq.getPassword();

        boolean successLogin = false;

        try {

            Connection conn = db.getConnection();
            UserDao userDataAccess = new UserDao(conn);
            AuthtokenDao authTokenDataAccess = new AuthtokenDao(conn);


            if (userDataAccess.findUsername(userName) != null) {
                if (userDataAccess.verifyCredentials(userName, passWord)) {

                    UUID myUUID = UUID.randomUUID();

                    String uniqueString = myUUID.toString();


                    returnResult.setAuthtoken(uniqueString);

                    returnResult.setUsername(userName);

                    returnResult.setPersonID(userDataAccess.findPersonID(userName));

                    returnResult.setSuccess(true);

                    Authtoken loginAuth = new Authtoken(uniqueString, userName);

                    authTokenDataAccess.insert(loginAuth);

                    db.closeConnection(true);

                    return returnResult;
                }
                //check to see if the password is incorrect

                else if (!userDataAccess.verifyCredentials(userName, passWord)) {

                    returnResult.setSuccess(false);
                    returnResult.setMessage("Error: incorrect password");
                    db.closeConnection(false);
                    return returnResult;


                }
            } else if (userDataAccess.find(userName) == null) {
                returnResult.setSuccess(false);
                returnResult.setMessage("Error: invalid username or password");
                db.closeConnection(false);
                return returnResult;
            }

        } catch (DataAccessException e) {
            returnResult.setMessage("Error caught");
            returnResult.setSuccess(false);
            e.printStackTrace();
            db.closeConnection(false);
            throw new RuntimeException(e);
        }


        return null;
    }

//end of class

}