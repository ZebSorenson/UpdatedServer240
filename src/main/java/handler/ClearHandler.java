package handler;

import RequestResult.ClearResult;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import service.ClearService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;


public class ClearHandler extends HandlerBase implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("You have arrived at the ClearHandler");

        boolean success = false;
        Gson gson = new Gson();
        ClearResult result = null;

        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {

                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                // Check to see if an "Authorization" header is present

                ClearService service = new ClearService();
                result = service.clear();
                success = result.getSuccess();


                if (success) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }

                OutputStream resBody = exchange.getResponseBody();
                String jSonResult = gson.toJson(result);
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
        }
    }
}
