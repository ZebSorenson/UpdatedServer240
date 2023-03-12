package daoTests;

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

    private PersonDao dataAccessPerson;

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
        dataAccessPerson = new PersonDao(conn);
        //Let's clear the database as well so any lingering data doesn't affect our tests
        dataAccessPerson.clear();
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
        dataAccessPerson.insert(testPerson);
        // Let's use a find method to get the person that we just put in back out.
        Person compareTest = dataAccessPerson.find(testPerson.getPersonID());
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
        dataAccessPerson.insert(testPerson);

        assertThrows(DataAccessException.class, () -> dataAccessPerson.insert(testPerson)); //we'll get a unique constraint fail error but that is expected in this test
    }

    @Test
    public void retrieveThreePersonPass() throws DataAccessException {

        //We'll create 2 new person objects (models) to insert. We alreaedy have the first from the set up method

        Person secondTestPerson = new Person("myID","secondUsername","Kevin", "Worthen", "m", "FatherKevin","kevinSpouse", "peggyID");

        Person thirdPersonTest = new Person("theProphetID", "prophetUserName","Russel", "Nelson","m","nelsonDad","NelsonMom","Wendy");

        //insert all three users into database
        dataAccessPerson.insert(testPerson); //from the set up method

        dataAccessPerson.insert(secondTestPerson);

        dataAccessPerson.insert(thirdPersonTest);

        Person firstCompareTest = dataAccessPerson.find(testPerson.getPersonID());
        assertNotNull(firstCompareTest);
        assertEquals(testPerson, firstCompareTest);

        Person secondCompareTest = dataAccessPerson.find(secondTestPerson.getPersonID());
        assertNotNull(secondCompareTest);
        assertEquals(secondTestPerson, secondCompareTest);

        Person thirdCompareTest = dataAccessPerson.find(thirdPersonTest.getPersonID());
        assertNotNull(thirdCompareTest);
        assertEquals(thirdPersonTest, thirdCompareTest);

    }



    @Test
    public void retrieveNullPersonFail()throws DataAccessException{

        assertNull(dataAccessPerson.find("not real person"));


    }

    @Test
    public void clearPersonTableTest() throws DataAccessException {

        dataAccessPerson.insert(testPerson); //insert person object into the table

        dataAccessPerson.clear(); //clear the table

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

    @Test
    public void negativePersonClear() throws DataAccessException {

        dataAccessPerson.insert(testPerson);

        dataAccessPerson.clear();

        assertNull(dataAccessPerson.find(testPerson.getPersonID()));
        //we should get a null value when we try to find a user that is no longer in the database

    }

    @Test
    public void findTruePerson() throws DataAccessException {

        dataAccessPerson.insert(testPerson);

        assertTrue(dataAccessPerson.findTrue(testPerson.getPersonID()));

        //we should get a true boolean value when we call findTrue on someone in the DB
    }

    @Test
    public void findFalsePerson() throws DataAccessException{

        assertFalse(dataAccessPerson.findTrue("InvalidPersonID"));
        //we should get a false boolean when we call findTrue for an invalid personID
    }

    @Test
    public void deleteValidPersonData()throws DataAccessException{

        dataAccessPerson.insert(testPerson);

        dataAccessPerson.deleteUserData(testPerson.getAssociatedUsername());

        assertNull(dataAccessPerson.find(testPerson.getPersonID()));

        //we insert our test person into the DB, delete the data associated with the userName of this person and then
        //we make sure that we get a null value when we go to look for this person
    }

    @Test
    public void deleteInvalidPersonData()throws DataAccessException{
        assertThrows(DataAccessException.class, () -> dataAccessPerson.deleteUserData(null));
        //we make sure we throw an appropriate exception if we pass in a null username to delete data for
    }

    @Test
    public void getAllPeoplePass() throws DataAccessException{

        Person Kevin = new Person("123", "Cosmo", "Kevin", "Worthen", "m","111", "111","111");

        Person Peggy = new Person("999", "Cosmo", "Peggy", "Worthen", "f", "999", "999","999");

        dataAccessPerson.insert(Kevin);

        dataAccessPerson.insert(Peggy);

        assertEquals(2, dataAccessPerson.getAllPeopleWithUsername("Cosmo").length);

        //we create 2 persons objects and then make sure that the length of the array we are getting back is of size 2


    }

    @Test
    public void getAllPeopleInvalidUserName() throws DataAccessException {

        assertEquals(0, dataAccessPerson.getAllPeopleWithUsername("Invalid User").length);
        //we should get an array of size 0 if we give this function a username that doesn't exist in the DB
    }









//end of class
}
