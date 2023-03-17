package serviceTests;
import RequestResult.ClearResult;
import RequestResult.EventResult;
import RequestResult.RegisterRequest;
import RequestResult.RegisterResult;
import dao.*;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ClearService;
import service.EventService;
import service.RegisterService;

public class AllEventsServiceTest {

    @BeforeEach
    public void setUp() throws DataAccessException{

        ClearService clear = new ClearService();

        clear.clear();


    }

    @AfterEach
    public void tearDown(){

    }


    @Test
    public void getAllValidEvents() throws DataAccessException {


        RegisterRequest regReq = new RegisterRequest();

        regReq.setUsername("Zebediah");
        regReq.setPassword("Password123");
        regReq.setEmail("Zeb@gmail.com");
        regReq.setFirstName("Zeb");
        regReq.setLastName("Sorenson");
        regReq.setGender("m");

        RegisterService regService = new RegisterService();

        RegisterResult regResult =  regService.register(regReq);

        //get all the events and make sure that is the right number of events

        EventService serviceEvent = new EventService();

        EventResult result = serviceEvent.GetAllEvents(regResult.getAuthtoken());


        System.out.println(result.getMessage());

        Assertions.assertTrue(result.getSuccess());

        Assertions.assertEquals(91,result.getData().length);

    }

    //for the negative, give it an invalid auth token..!!
}