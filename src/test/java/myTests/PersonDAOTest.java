package myTests;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDao;

import model.Person;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {

    private Database db;

    private Person testPerson;

    private PersonDao ePerson;

    @BeforeEach
    public void setUp() throws DataAccessException {
        // Here we can set up any classes or variables we will need for each test
        // lets create a new instance of the Database class
        db = new Database();
        // and a new event with random data
        testPerson = new Person("RandomPersonID", "UserNameOfRandomPerson", "Megatron",
                "Transformer", "m", "FatherTransformer", "motherTransformer",
                "megatronSpouse");

        // Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Then we pass that connection to the EventDAO, so it can access the database.
        ePerson = new PersonDao(conn);
        //Let's clear the database as well so any lingering data doesn't affect our tests
        ePerson.clear();
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
        // Start by inserting a person into the database.
        ePerson.insert(testPerson);
        // Let's use a find method to get the person that we just put in back out.
        Person compareTest = ePerson.find(testPerson.getPersonID());
        // First lets see if our find method found anything at all. If it did then we know that we got
        // something back from our database.
        assertNotNull(compareTest);
        // Now lets make sure that what we put in is the same as what we got out. If this
        // passes then we know that our insert did put something in, and that it didn't change the
        // data in any way.
        // This assertion works by calling the equals method in the Event class.
        assertEquals(testPerson, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException{
        ePerson.insert(testPerson);

        assertThrows(DataAccessException.class, () -> ePerson.insert(testPerson)); //we'll get a unique constraint fail error but that is expected in this test
    }

    @Test
    public void retrieveThreePersonPass() throws DataAccessException {

        //We'll create 2 new person objects (models) to insert. We alreaedy have the first from the set up method

        Person secondTestPerson = new Person("myID","secondUsername","Kevin", "Worthen", "m", "FatherKevin","kevinSpouse", "peggyID");

        Person thirdPersonTest = new Person("theProphetID", "prophetUserName","Russel", "Nelson","m","nelsonDad","NelsonMom","Wendy");

        //insert all three users into database
        ePerson.insert(testPerson); //from the set up method

        ePerson.insert(secondTestPerson);

        ePerson.insert(thirdPersonTest);

        Person firstCompareTest = ePerson.find(testPerson.getPersonID());
        assertNotNull(firstCompareTest);
        assertEquals(testPerson, firstCompareTest);

        Person secondCompareTest = ePerson.find(secondTestPerson.getPersonID());
        assertNotNull(secondCompareTest);
        assertEquals(secondTestPerson, secondCompareTest);

        Person thirdCompareTest = ePerson.find(thirdPersonTest.getPersonID());
        assertNotNull(thirdCompareTest);
        assertEquals(thirdPersonTest, thirdCompareTest);

    }



    @Test
    public void retrieveNullPersonFail()throws DataAccessException{

        assertNull(ePerson.find("not real person"));


    }

    @Test
    public void clearTableTest() throws DataAccessException, SQLException {

        ePerson.insert(testPerson); //insert person object into the table

        ePerson.clear(); //clear the table

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            conn = db.getConnection();

            // make a SELECT statement to see if the table is empty
            statement = conn.createStatement();

            resultSet = statement.executeQuery("SELECT COUNT(*) FROM PERSON");

            resultSet.next();

            int count = resultSet.getInt(1);

            // check that the table is empty by checking to see that the count is 1
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

    //can also just run find on who you inserted...







//end of class
}
