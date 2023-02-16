package myTests;

import dao.DataAccessException;
import dao.Database;
import dao.EventDao;
import dao.UserDao;

import model.Event;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

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

    private UserDao user_dao;

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
        user_dao = new UserDao(conn);
        //Let's clear the database as well so any lingering data doesn't affect our tests
        user_dao.clear();
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
        user_dao.insert(myTestUser);
        // Let's use a find method to get the event that we just put in back out.
        User compareTest = user_dao.find((myTestUser.getPersonID()));
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
    public void insertThreeUsersPass() throws DataAccessException{ //insert 3 users into the db and then check to make sure there is exactly 3 users in the user table


        //create 2 new users (We already have the first from the set up before all method

        User secondTestUser = new User("ClaytonNation", "password_123","randomEmail@gmail.com","Clayton", "Young", "M", "1010");

        User thirdTestUser = new User("SuperGirl","superManPassword","super_girl@gmail.com","Kara", "Zor", "F", "1111");

        //Insert all 3 users

        user_dao.insert(myTestUser); //already have this user from setup so we'll use them here as well

        user_dao.insert(secondTestUser);

        user_dao.insert(thirdTestUser);

        //make sure all of the users are in the database

        User compareTest = user_dao.find((myTestUser.getPersonID())); //new user for comparison
        assertNotNull(compareTest); // make sure we're not getting null back for this user
        assertEquals(myTestUser, compareTest); //make sure the first two users are the same
        //second user comparison
        User secondCompareTest = user_dao.find(secondTestUser.getPersonID());
        assertNotNull(secondCompareTest);
        assertEquals(secondTestUser, secondCompareTest);
        //third user comparison
        User thirdCompareTest = user_dao.find(thirdTestUser.getPersonID());
        assertNotNull(thirdCompareTest);
        assertEquals(thirdTestUser, thirdCompareTest);


    }

    @Test
    public void insertFail() throws DataAccessException{

        //getting an exception when we try to insert twice (this is copied from the Events test example code that is given to us

        user_dao.insert(myTestUser);

        assertThrows(DataAccessException.class, () -> user_dao.insert(myTestUser));

    }

    @Test
    public void insertFail_SameUserID() throws DataAccessException{
        // we'll try to insert 2 users with all different attributes but have the same personID

        User firstTestUser = new User("ClaytonNation", "password_123","randomEmail@gmail.com","Clayton", "Young", "M", "1010");

        User SecondTestUser = new User("SuperGirl","superManPassword","super_girl@gmail.com","Kara", "Zor", "F", "1010");

        user_dao.insert(firstTestUser); //normal insertion

        assertThrows(DataAccessException.class, () -> user_dao.insert(SecondTestUser)); //should get the data access error bc we then try to insert the second user that has the same personID

        //you'll get some red errors about UNIQUE constraints failing on the personID but that is what we want for this test
    }

    @Test
    public void clearTableTest() throws DataAccessException, SQLException {

        user_dao.insert(myTestUser); //insert Event object into the table

        user_dao.clear(); //clear the table

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



//end of class
}
