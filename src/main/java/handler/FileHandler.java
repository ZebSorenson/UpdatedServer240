package handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

public class FileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {


                String urlPath = exchange.getRequestURI().toString();

                if (urlPath == null || urlPath.equals("/")) { //always double check the type of equals/== you are using
                    urlPath = "/index.html";

                }

                String filePath = "web" + urlPath;

                File file = new File(filePath);

                if (!file.exists()) {

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                    OutputStream respBody = exchange.getResponseBody();
                    File errorFile = new File("web/HTML/404.html");
                    Files.copy(errorFile.toPath(), respBody);
                    respBody.close();


                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                    exchange.getResponseBody().close();

                }

                OutputStream respBody = exchange.getResponseBody();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Files.copy(file.toPath(), respBody);


                exchange.getResponseBody().close();

            }
        } catch (Exception e) {
            // throw new RuntimeException(e);

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }

    }
}

