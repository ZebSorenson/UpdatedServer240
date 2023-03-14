package handler;

import RequestResult.LoginRequest;
import RequestResult.LoginResult;
import RequestResult.RegisterRequest;
import RequestResult.RegisterResult;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.RegisterService;

import java.io.*;
import java.net.HttpURLConnection;


public class RegisterHandler extends HandlerBase implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {

            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {

                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                // Check to see if an "Authorization" header is present


                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();

                // Read JSON string from the input stream
                String reqData = readString(reqBody);


                // Display/log the request JSON data
                System.out.println(reqData);

                Gson gson = new Gson();

                RegisterRequest request = (RegisterRequest) gson.fromJson(reqData, RegisterRequest.class); //turning json string into a request

                RegisterService service = new RegisterService();


                RegisterResult result = service.register(request); // THIS IS TAKING CARE OF 1. going to service class which will use the dao classes to check if user exists, then
                //if yes, then it will send back a result object with an Auth token

                if (result.getSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    // If the result is not successful, send HTTP_BAD_REQUEST
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }


                OutputStream resBody = exchange.getResponseBody();

                String jSonResult = gson.toJson(result); //may need response body? should work without but just in case.

                writeString(jSonResult, resBody);

                resBody.close();

            }


        } catch (IOException e) {
            // Some kind of internal error has occurred inside the server (not the
            // client's fault), so we return an "internal server error" status code
            // to the client.
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);

            // We are not sending a response body, so close the response body
            // output stream, indicating that the response is complete.
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }


    }


    //end of class
}