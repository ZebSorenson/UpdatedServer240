package handler;

import RequestResult.*;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.LoadService;

import java.io.*;
import java.net.HttpURLConnection;
import java.sql.SQLException;


public class LoadHandler extends HandlerBase implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // exchange.getRequestURI().toString(); this will give the info from the URL
        //Split method on a String object and it will split the String into an array. Can give it / and seperate as needed


        boolean success = false;

        try {

            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {

                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();

                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();

                // Read JSON string from the input stream
                String reqData = readString(reqBody);


                // Display/log the request JSON data
                //System.out.println(reqData);

                Gson gson = new Gson();
                System.out.println("Making request");
                LoadRequest request = (LoadRequest) gson.fromJson(reqData, LoadRequest.class); //turning json string into a request

                LoadService service = new LoadService();

                System.out.println("Entering the service function");


                LoadResult result = service.load(request); // THIS IS TAKING CARE OF 1. going to service class which will use the dao classes to check if user exists, then
                //if yes, then it will send back a result object with an Auth token

                if (result.getSuccess() == true) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    success = true;
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }


                OutputStream resBody = exchange.getResponseBody();

                String jSonResult = gson.toJson(result); //may need response body? should work without but just in case.

                writeString(jSonResult, resBody);

                resBody.close();

                exchange.getResponseBody().close();


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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }


    }


    //end of class
}
