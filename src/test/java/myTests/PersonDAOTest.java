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



}
