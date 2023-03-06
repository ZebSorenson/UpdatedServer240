package handler;

import RequestResult.FillResult;
import RequestResult.LoginRequest;
import RequestResult.LoginResult;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import service.FillService;
import service.LoginService;

import java.io.*;
import java.net.HttpURLConnection;
import java.sql.SQLException;


public class FillHandler implements HttpHandler {

    final int defaultGeneration = 4; //default number of generations if not specified by the user.
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // exchange.getRequestURI().toString(); this will give the info from the URL
        //Split method on a String object and it will split the String into an array. Can give it / and seperate as needed

        //STILL NEED DAO TO HANDLE CHECKING IF THERE IS DATA CONNECTED TO THE USER. PROBABLY DO THIS IN THE SERVICE CLASS.

        String userName = null;
        Integer numGeneration = null;

        String requestUri = exchange.getRequestURI().toString();
        String[] parts = requestUri.split("/");
        if (parts.length == 3 && parts[1].equals("fill")) {
             userName = parts[2];
             System.out.println(userName);
            // set name in exchange object
        } else if (parts.length == 4 && parts[1].equals("fill")) {
             userName = parts[2];
              numGeneration = Integer.parseInt(parts[3]);
             System.out.println(numGeneration + " Generations:"+ " Username, "+userName);
            // set name and username in exchange object
        } else {
            // invalid URL pattern
        }



        boolean success = false;

        try {

            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {


                 Gson gson = new Gson();
                 FillResult result = new FillResult();



                    FillService service = new FillService(userName); //create the new Fill service object and give it the username for the person we are filling.
                    //note, this is just a string, not an actual user. We'll need to use the Dao classes to actually get the user

                    if(parts.length==4){
                         result = service.fill(userName, numGeneration); // THIS IS TAKING CARE OF 1. going to service class which will use the dao classes to check if user exists, then
                        //if yes, then it will send back a result object with an Auth token


                    }else if(parts.length==3){
                        result = service.fill(userName, defaultGeneration); // this is the default number of generations
                    }


                    // service.login(request);

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    OutputStream resBody = exchange.getResponseBody();

                    String jSonResult = gson.toJson(result); //may need response body? should work without but just in case.

                    writeString(jSonResult, resBody);

                    resBody.close();

                    success = true;

            }

            if (!success) {
                // The HTTP request was invalid somehow, so we return a "bad request"
                // status code to the client.
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                // We are not sending a response body, so close the response body
                // output stream, indicating that the response is complete.
                exchange.getResponseBody().close();
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
