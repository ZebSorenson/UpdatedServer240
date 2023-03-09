package handler;

import RequestResult.*;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.EventService;
import service.PersonIDService;
import service.PersonService;
import service.RegisterService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;


public class EventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // exchange.getRequestURI().toString(); this will give the info from the URL
        //Split method on a String object and it will split the String into an array. Can give it / and seperate as needed




        boolean success = false;

        try {



            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {

                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();

                // Check to see if an "Authorization" header is present
                if (reqHeaders.containsKey("Authorization")) {

                    // Extract the auth token from the "Authorization" header
                    String authToken = reqHeaders.getFirst("Authorization");

                    Gson gson = new Gson();
                    EventService service = new EventService();

                    // Get all events using the provided auth token
                    EventResult result = service.GetAllEvents(authToken);

                    // If the result is successful, send HTTP_OK
                    if (result.getSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    } else {
                        // If the result is not successful, send HTTP_BAD_REQUEST
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }

                    // Get the response body output stream and write the JSON result
                    OutputStream resBody = exchange.getResponseBody();
                    String jSonResult = gson.toJson(result);
                    writeString(jSonResult, resBody);
                    resBody.close();

                    success = true;
                }
            }

            if (!success) {
                // The HTTP request was invalid somehow, so we return a "bad request"
                // status code to the client.
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                // We are not sending a response body, so close the response body
                // output stream, indicating that the response is complete.
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

        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    /*
        The readString method shows how to read a String from an InputStream.
    */
    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    //good idea to put these two above functions in a shared class for all to use



    //end of class
}
