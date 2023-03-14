package serviceTests;
import RequestResult.ClearResult;
import RequestResult.FillResult;
import RequestResult.RegisterRequest;
import RequestResult.RegisterResult;
import dao.*;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ClearService;
import service.FillService;
import service.RegisterService;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class FillServiceTest {



    @Test
    public void completeFill() throws DataAccessException, SQLException, FileNotFoundException {
        //register request
        //register

        //call fill on the person and that will default to 4

        RegisterRequest regRequest = new RegisterRequest();

        regRequest.setUsername("Zebediah");
        regRequest.setFirstName("Zeb");
        regRequest.setLastName("Sorenson");
        regRequest.setEmail("zeb@gmail.com");
        regRequest.setPassword("123");
        regRequest.setGender("m");

        RegisterService service = new RegisterService();

        RegisterResult regResult = service.register(regRequest);

        FillService fill = new FillService(regRequest.getUsername());

        FillResult fill_Result = fill.fill(regResult.getUsername(),1);

        //check result and number of people

        Assertions.assertTrue(fill_Result.getSuccess());

        Assertions.assertEquals(fill_Result.getMessage(), "Successfully added 3 persons and 7 events to the database.");

        //add more generations to be tested
    }

    @Test
    public void negativeGenerations()throws DataAccessException, SQLException, FileNotFoundException{

        RegisterRequest regRequest = new RegisterRequest();

        regRequest.setUsername("Zebediah");
        regRequest.setFirstName("Zeb");
        regRequest.setLastName("Sorenson");
        regRequest.setEmail("zeb@gmail.com");
        regRequest.setPassword("123");
        regRequest.setGender("m");

        RegisterService service = new RegisterService();

        RegisterResult regResult = service.register(regRequest);

        FillService fill = new FillService(regRequest.getUsername());

        FillResult fill_Result = fill.fill(regResult.getUsername(),-1);

        //check result and number of people

        Assertions.assertFalse(fill_Result.getSuccess());
        Assertions.assertEquals(fill_Result.getMessage(), "Invalid number of generations.");

    }







    //end of class
}