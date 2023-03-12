package handler;

import RequestResult.*;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.PersonIDService;
import service.RegisterService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;


public class PersonIDHandler extends HandlerBase implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // exchange.getRequestURI().toString(); this will give the info from the URL
        //Split method on a String object and it will split the String into an array. Can give it / and seperate as needed


        try {

            // get the request URI from the exchange
            URI uri = exchange.getRequestURI();
            String uriString = uri.toString();

// split the URI into its constituent parts
            String[] parts = uriString.split("/");

// extract the person ID from the URI
            String personId = parts[2];

// extract the "person" part of the URI
            String person = parts[1];


            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {

                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                // Check to see if an "Authorization" header is present
                if (reqHeaders.containsKey("Authorization")) { // what does this mean?

                    // Extract the auth token from the "Authorization" header
                    String authToken = reqHeaders.getFirst("Authorization");

                    // Verify that the auth token is the one we're looking for
                    // (this is not realistic, because clients will use different
                    // auth tokens over time, not the same one all the time).
                    // if (authToken.equals("afj232hj2332")) { // I don't need this correct?

                    // Extract the JSON string from the HTTP request body

                    // Get the request body input stream
                    InputStream reqBody = exchange.getRequestBody();

                    // Read JSON string from the input stream
                    String reqData = readString(reqBody);


                    // Display/log the request JSON data
                    System.out.println(reqData);

                    Gson gson = new Gson();


                    PersonIDService service = new PersonIDService();

                    PersonIDResult result = service.RetrievePersonID(personId, authToken);

                    if (result.getSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    } else {
                        // If the result is not successful, send HTTP_BAD_REQUEST
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }

                    // THIS IS TAKING CARE OF 1. going to service class which will use the dao classes to check if user exists, then
                    //if yes, then it will send back a result object with an Auth token

                    // service.login(request);

                   // exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    OutputStream resBody = exchange.getResponseBody();

                    String jSonResult = gson.toJson(result); //may need response body? should work without but just in case.

                    writeString(jSonResult, resBody);

                    resBody.close();


                }
            }

        }
        catch (IOException e) {
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
