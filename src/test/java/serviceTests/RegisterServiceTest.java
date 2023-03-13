package serviceTests;
import RequestResult.ClearResult;
import RequestResult.RegisterRequest;
import RequestResult.RegisterResult;
import dao.*;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ClearService;
import service.RegisterService;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.*;

public class RegisterServiceTest {

    @BeforeEach
    public void setUp() throws DataAccessException{


    }

    @AfterEach
    public void tearDown(){

    }


    @Test
    public void registerNewUserTest() throws DataAccessException {

        RegisterRequest regReq = new RegisterRequest();

        regReq.setUsername("Zebediah");
        regReq.setPassword("Password123");
        regReq.setEmail("Zeb@gmail.com");
        regReq.setFirstName("Zeb");
        regReq.setLastName("Sorenson");
        regReq.setGender("m");

        RegisterService regService = new RegisterService();

        RegisterResult regResult =  regService.register(regReq);

        assertTrue(regResult.getSuccess());

        assertEquals(regResult.getUsername(), "Zebediah");

        assertNotNull(regResult.getAuthtoken());

        assertNotNull(regResult.getPersonID());

        //make a request
        //pass that to the service
        //what do you get back?
    }

    @Test
    public void registerUserSecondTime() throws DataAccessException{

        RegisterRequest regReq = new RegisterRequest();

        regReq.setUsername("Zebediah");
        regReq.setPassword("Password123");
        regReq.setEmail("Zeb@gmail.com");
        regReq.setFirstName("Zeb");
        regReq.setLastName("Sorenson");
        regReq.setGender("m");

        RegisterService regService = new RegisterService();

        RegisterResult regResult =  regService.register(regReq);

        RegisterResult secondResult = regService.register(regReq);


        assertFalse(secondResult.getSuccess()); // we shouldn't be able to register another user with the same username

    }

    //end of class
}
