package myTests;

import dao.DataAccessException;
import dao.Database;
import dao.UserDao;

import model.User;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;


public class UserDAOTest {

    private Database db;

    private User myTestUser;

    private UserDao dataAccessUser;

    @BeforeEach
    public void setUp() throws DataAccessException {
        // Here we can set up any classes or variables we will need for each test
        // lets create a new instance of the Database class
        db = new Database();
        // and a new event with random data
        myTestUser = new User("Zebulon", "Password123", "myEmail@gmail.com",
                "Zeb", "Sorenson", "M", "54321");

        // Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Then we pass that connection to the EventDAO, so it can access the database.
        dataAccessUser = new UserDao(conn);
        //Let's clear the database as well so any lingering data doesn't affect our tests
        dataAccessUser.clear();
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
        dataAccessUser.insert(myTestUser);
        // Let's use a find method to get the event that we just put in back out.
        User compareTest = dataAccessUser.find((myTestUser.getPersonID()));
        // First lets see if our find method found anything at all. If it did then we know that we got
        // something back from our database.
        assertNotNull(compareTest);
        // Now lets make sure that what we put in is the same as what we got out. If this
        // passes then we know that our insert did put something in, and that it didn't change the
        // data in any way.
        // This assertion works by calling the equals method in the Event class.
        assertEquals(myTestUser, compareTest);
        //
    }

    @Test
    public void insertFail() throws DataAccessException {

        //getting an exception when we try to insert twice (this is copied from the Events test example code that is given to us

        dataAccessUser.insert(myTestUser);

        assertThrows(DataAccessException.class, () -> dataAccessUser.insert(myTestUser));

    }

    @Test
    public void insertAndFindThreeUsers() throws DataAccessException { //insert 3 users into the db and then check to make sure there is exactly 3 users in the user table


        //create 2 new users (We already have the first from the set up before all method

        User secondTestUser = new User("ClaytonNation", "password_123", "randomEmail@gmail.com", "Clayton", "Young", "M", "1010");

        User thirdTestUser = new User("SuperGirl", "superManPassword", "super_girl@gmail.com", "Kara", "Zor", "F", "1111");

        //Insert all 3 users

        dataAccessUser.insert(myTestUser); //already have this user from setup so we'll use them here as well

        dataAccessUser.insert(secondTestUser);

        dataAccessUser.insert(thirdTestUser);

        //make sure all of the users are in the database

        User compareTest = dataAccessUser.find((myTestUser.getPersonID())); //new user for comparison
        assertNotNull(compareTest); // make sure we're not getting null back for this user
        assertEquals(myTestUser, compareTest); //make sure the first two users are the same
        //second user comparison
        User secondCompareTest = dataAccessUser.find(secondTestUser.getPersonID());
        assertNotNull(secondCompareTest);
        assertEquals(secondTestUser, secondCompareTest);
        //third user comparison
        User thirdCompareTest = dataAccessUser.find(thirdTestUser.getPersonID());
        assertNotNull(thirdCompareTest);
        assertEquals(thirdTestUser, thirdCompareTest);


    }


    @Test
    public void retrieveSameIDFail() throws DataAccessException {
        // we'll try to insert 2 users with all different attributes but have the same personID

        User firstTestUser = new User("ClaytonNation", "password_123", "randomEmail@gmail.com", "Clayton", "Young", "M", "1010");

        User SecondTestUser = new User("SuperGirl", "superManPassword", "super_girl@gmail.com", "Kara", "Zor", "F", "1010");

        dataAccessUser.insert(firstTestUser); //normal insertion

        assertThrows(DataAccessException.class, () -> dataAccessUser.insert(SecondTestUser)); //should get the data access error bc we then try to insert the second user that has the same personID

        //you'll get some red errors about UNIQUE constraints failing on the personID but that is what we want for this test
    }

    @Test
    public void clearTableTest() throws DataAccessException, SQLException {

        dataAccessUser.insert(myTestUser); //insert Event object into the table

        dataAccessUser.clear(); //clear the table

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            conn = db.getConnection();

            // make a SELECT statement to see if the table is empty
            statement = conn.createStatement();

            resultSet = statement.executeQuery("SELECT COUNT(*) FROM USER");

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
    public void ClearUser() throws DataAccessException {

        dataAccessUser.insert(myTestUser);

        dataAccessUser.clear();

        assertNull(dataAccessUser.findUser(myTestUser.getUsername()));

        //we are making sure we are getting a null value when we look for a user in the db after it has been cleared

    }

    @Test
    public void validCredentials() throws DataAccessException {

        dataAccessUser.insert(myTestUser);

        assertTrue(dataAccessUser.verifyCredentials("Zebulon", "Password123"));
        //we should get a true value when we pass in the correct username and password for a user in the database
    }

    @Test
    public void invalidCredentials() throws DataAccessException {

        assertFalse(dataAccessUser.verifyCredentials("False username", "False password"));

        //we should get a false boolean when we pass in an invalid password and username
    }

    @Test
    public void findValidUserName() throws DataAccessException {

        dataAccessUser.insert(myTestUser);

        User matchingUser = dataAccessUser.findUser(myTestUser.getUsername());

        assertEquals(matchingUser, myTestUser);

        //we set a user to the user object we get back from passing in our test user from setup's username
        //then we make sure the two users are equal
    }

    @Test
    public void findInvalidUserName() throws DataAccessException {


        assertEquals(null, dataAccessUser.findUser(myTestUser.getUsername()));
        //we should get a null value if we look for a user that is not in the database
    }

    @Test
    public void getValidUsername() throws DataAccessException {

        dataAccessUser.insert(myTestUser);

        assertEquals("Zebulon", dataAccessUser.findUsername(myTestUser.getUsername()));
        //we get the correct string back when we give the username of a user
    }

    @Test
    public void getInvalidUsername() throws DataAccessException {

        assertNull(dataAccessUser.findUsername("InvalidUsername"));
        //we get a null value when we try to find a username that does not exist in the database
    }

    @Test
    public void getValidPersonID() throws DataAccessException {

        dataAccessUser.insert(myTestUser);

        assertEquals(myTestUser.getPersonID(), dataAccessUser.findPersonID(myTestUser.getUsername()));

        //we should get back the correct personID when given the correctUsername
    }

    @Test
    public void getInvalidPersonID() throws DataAccessException {

        assertNull(dataAccessUser.findPersonID("InvalidInfo"));
        //we should get a null value when we search for a personID with an invalid username

    }


//end of class
}
