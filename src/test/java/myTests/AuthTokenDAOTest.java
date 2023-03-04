package myTests;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthTokenDAOTest {

    private Database db;

    private Authtoken authTokenObject;

    private AuthtokenDao dataAccessAuthToken;

    @BeforeEach
    public void setUp() throws DataAccessException {
        // Here we can set up any classes or variables we will need for each test
        // lets create a new instance of the Database class
        db = new Database();
        // and a new event with random data
        authTokenObject = new Authtoken(UUID.randomUUID().toString(), "Zebinho");

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
}
