package daoTests;

import dao.*;
import model.Authtoken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {

    private Database db;

    private Authtoken authTokenObject;

    private AuthtokenDao dataAccessAuthToken;

    private String authTokenString;

    @BeforeEach
    public void setUp() throws DataAccessException {
        // Here we can set up any classes or variables we will need for each test
        // lets create a new instance of the Database class
        db = new Database();
        // and a new event with random data

        authTokenString = UUID.randomUUID().toString();
        //we create a unique string for our auth token to be used.

        authTokenObject = new Authtoken(authTokenString, "Zebinho");

        // Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Then we pass that connection to the EventDAO, so it can access the database.
        dataAccessAuthToken = new AuthtokenDao(conn);
        //Let's clear the database as well so any lingering data doesn't affect our tests
        dataAccessAuthToken.clear();
    }

    @AfterEach
    public void tearDown() {
        // Here we close the connection to the database file, so it can be opened again later.
        // We will set commit to false because we do not want to save the changes to the database
        // between test cases.
        db.closeConnection(false);
    }

    @Test
    public void clearPass() throws SQLException, DataAccessException {

        dataAccessAuthToken.insert(authTokenObject);

        dataAccessAuthToken.clear();

        Connection conn = null;

        Statement statement = null;

        ResultSet resultSet = null;

        try {

            conn = db.getConnection();

            // make a SELECT statement to see if the table is empty
            statement = conn.createStatement();

            resultSet = statement.executeQuery("SELECT COUNT(*) FROM AuthorizationToken");

            resultSet.next();

            int count = resultSet.getInt(1);

            // Assert that the table is empty by checking to see that the count is 1
            assertEquals(0, count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the ResultSet and Statement (connection is handled in the after each teardown method
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void clearTestEmptyDB() throws DataAccessException {

        dataAccessAuthToken.clear();
        //first we clear the AuthToken table in the database

        assertNull(dataAccessAuthToken.findAuthToken(UUID.randomUUID().toString()));
        //then we check to make sure we are getting a null value when we look for an AuthToken that doesn't exist.

    }

    @Test
    public void validAuthTokenPass() throws DataAccessException, SQLException {

        dataAccessAuthToken.insert(authTokenObject);
        // first we insert the auth token we create in our setup into the database

        //here we simply check to make sure our AuthTokenString variable is connected to the username we create in our setup
        assertTrue(dataAccessAuthToken.isValidAuth("Zebinho", authTokenString));

        //since these are manually created together in our setup. Checking an assertTrue will certify that our function
        //is successfully checking to see in the database if the provided authtoken and username are connected to eachother

    }

    @Test
    public void invalidAuthToken() throws DataAccessException, SQLException {

        dataAccessAuthToken.insert(authTokenObject);
        //we again insert the auth token object we create in setup into the databse

        assertFalse(dataAccessAuthToken.isValidAuth("UtahUtes", authTokenString));
        //now we check to see that we get a false return value when comparing an incorrect username with our AuthToken
    }

    @Test
    public void findValidAuthToken() throws DataAccessException, SQLException {

        Authtoken authToken = new Authtoken("1875", "Cosmo");
        // create a new Authtoken and insert it into the database

        dataAccessAuthToken.insert(authToken);
        //Cosmo founded BYU, right?

        Authtoken authTokenFound = dataAccessAuthToken.findAuthToken("1875");
        // call the DAO method to find the Authtoken

        assertNotNull(authTokenFound);

        assertEquals("1875", authTokenFound.getAuthtoken());

        assertEquals("Cosmo", authTokenFound.getUsername());
        // assert that the Authtoken was found
    }

    @Test
    public void findInvalidAuthToken() throws DataAccessException {

        //we try to get back and AuthToken but we give it an invalid authtoken string

        Authtoken falseToken = dataAccessAuthToken.findAuthToken("BYU should sell energy drinks on campus");

        assertNull(falseToken);
        //we should get null back because this authtoken doesn't exist in the database
    }

    @Test
    public void validAuthTokenInsert() throws SQLException, DataAccessException {

        dataAccessAuthToken.insert(authTokenObject);
        //we insert our authtoken object from our setup

        Authtoken foundAuthToken = dataAccessAuthToken.findAuthToken(authTokenString);
        //we create a second Authtoken to match the one we inserted

        assertNotNull(foundAuthToken);
        //we make sure the token we just created isn't null

        assertEquals(authTokenString, authTokenObject.getAuthtoken());
        //now we make sure the token string and the username match
        assertEquals("Zebinho", authTokenObject.getUsername());
        //now we make sure the token string and the username match
    }

    @Test
    public void invalidAuthTokenInsert() throws SQLException, DataAccessException {

        Authtoken nullToken = new Authtoken(null, null);

        assertThrows(DataAccessException.class, () -> dataAccessAuthToken.insert(nullToken));
        //we cannot insert a null authtoken so we make sure that trying to do so throws the appropriate exceptions
    }

    //end of class
}
