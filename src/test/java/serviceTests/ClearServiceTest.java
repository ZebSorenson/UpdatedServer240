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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearServiceTest {


    @BeforeEach
    public void setUp() throws DataAccessException{


    }

    @AfterEach
    public void tearDown(){

    }

    @Test
    public void successfulClear() throws  DataAccessException {

        User testUser = new User("Zebediah", "password123", "Zeb@byu/.edu", "Zeb", "Sorenson", "m","123");

        RegisterService registerUser = new RegisterService();

        RegisterRequest request = new RegisterRequest();

        request.setUsername("Zebediah");
        request.setPassword("Password123");
        request.setEmail("zeb@byu.edu");
        request.setFirstName("Zeb");
        request.setLastName("Sorenson");
        request.setGender("m");


        RegisterResult result = registerUser.register(request);

        ClearService service = new ClearService();

        ClearResult clearResultObject = service.clear();

        assertTrue(clearResultObject.getSuccess());

        //we create a request object for clear and then call the clear service and check to see that
        //the success is set to true on the result object

    }

    //not certain how we can create a negative test case for the clear service?

    //end of class
}