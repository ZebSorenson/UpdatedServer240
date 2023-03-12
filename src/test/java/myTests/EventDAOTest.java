package myTests;

import dao.DataAccessException;
import dao.Database;
import model.Event;
import dao.EventDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
public class EventDAOTest {
    private Database db;
    private Event bestEvent;
    private EventDao dataAccessEvent;

    @BeforeEach
    public void setUp() throws DataAccessException {
        // Here we can set up any classes or variables we will need for each test
        // lets create a new instance of the Database class
        db = new Database();
        // and a new event with random data
        bestEvent = new Event("Biking_123A", "Gale", "Gale123A",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);

        // Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Then we pass that connection to the EventDAO, so it can access the database.
        dataAccessEvent = new EventDao(conn);
        //Let's clear the database as well so any lingering data doesn't affect our tests
        dataAccessEvent.clear();
    }

    @AfterEach
    public void tearDown() {
        // Here we close the connection to the database file, so it can be opened again later.
        // We will set commit to false because we do not want to save the changes to the database
        // between test cases.
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        // Start by inserting an event into the database.
        dataAccessEvent.insert(bestEvent);
        // Let's use a find method to get the event that we just put in back out.
        Event compareTest = dataAccessEvent.find(bestEvent.getEventID());
        // First lets see if our find method found anything at all. If it did then we know that we got
        // something back from our database.
        assertNotNull(compareTest);
        // Now lets make sure that what we put in is the same as what we got out. If this
        // passes then we know that our insert did put something in, and that it didn't change the
        // data in any way.
        // This assertion works by calling the equals method in the Event class.
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        // Let's do this test again, but this time lets try to make it fail.
        // If we call the method the first time the event will be inserted successfully.
        dataAccessEvent.insert(bestEvent);

        // However, our sql table is set up so that the column "eventID" must be unique, so trying to insert
        // the same event again will cause the insert method to throw an exception, and we can verify this
        // behavior by using the assertThrows assertion as shown below.

        // Note: This call uses a lambda function. A lambda function runs the code that comes after
        // the "()->", and the assertThrows assertion expects the code that ran to throw an
        // instance of the class in the first parameter, which in this case is a DataAccessException.
        assertThrows(DataAccessException.class, () -> dataAccessEvent.insert(bestEvent));
    }

    @Test
    public void clearEventsTable() throws DataAccessException, SQLException {

        dataAccessEvent.insert(bestEvent); //insert Event object into the table

        dataAccessEvent.clear(); //clear the table

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            conn = db.getConnection();

            // Execute a SELECT statement to check if the table is empty
            statement = conn.createStatement();

            resultSet = statement.executeQuery("SELECT COUNT(*) FROM EVENTS");

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
    public void clearAccessEmptyDB() throws DataAccessException {

        dataAccessEvent.clear();
        //we first clear the table

        assertNull(dataAccessEvent.find(bestEvent.getEventID()));
        //we should then get a null value when we try to find an event that doesn't exist in the DB

    }

    @Test
    public void findValidEvent()throws DataAccessException{

        dataAccessEvent.insert(bestEvent);
        //we insert the event that we create in our setup

        Event matchingEvent = dataAccessEvent.find(bestEvent.getEventID());
        //we then create a new event that is set the return result we get back from find with our setup event
        //so they should be the same event

        assertEquals(bestEvent,matchingEvent);
        //we then assert that our two events are the same
    }

    @Test
    public void findInvalidEvent()throws DataAccessException{

        assertNull(dataAccessEvent.find("this event doesn't exist!"));
        //we should get a null value back if we call find on an eventID that doesn't exist
    }


    @Test
    public void deleteUserDataPass()throws DataAccessException{

        dataAccessEvent.insert(bestEvent);
        //first we insert our event from setup

        dataAccessEvent.deleteUserData(bestEvent.getAssociatedUsername());
        //we then delete the event data connected to the associated username

        assertNull(dataAccessEvent.find(bestEvent.getEventID()));
        //we check to make sure we can a null value for the event we just deleted based on the username
    }

    @Test
    public void deleteInvalidUserData()throws DataAccessException{

        assertThrows(DataAccessException.class, () -> dataAccessEvent.deleteUserData(null));
        //we should get a DataAcessException if we try to delete data for a username that doesn't exist in the database/is null
    }

    @Test
    public void getAllEventsPass()throws DataAccessException{

        dataAccessEvent.clear();

        Event firstEvent = new Event("123", "Cosmo", "1875", 10f, 10f,
                "USA","Provo","Graduation",2023);

        Event secondEvent = new Event("456", "Cosmo", "1950", 10f, 10f,
                "USA","Provo","Speech",2023);

        dataAccessEvent.insert(firstEvent);

        dataAccessEvent.insert(secondEvent);

        assertEquals(2,dataAccessEvent.getAllEventsForUsername("Cosmo").length);

        //We clear the events table and then create two new events and insert them into the database
        //with the associatedUsername as "Cosmo". We then make sure that we are getting a size of 2 when we get all of our events

    }

    @Test
    public void getAllEventsNullUsername() throws DataAccessException{

        dataAccessEvent.clear();

        assertThrows(DataAccessException.class, () -> dataAccessEvent.getAllEventsForUsername(null));
        //we make sure we throw an appropriate exception if we look for events in the DB for a username that is null

    }

    //end of testing class
}
