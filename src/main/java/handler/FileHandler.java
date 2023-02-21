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

        //video talks about appending web/ in front of any path that we create here at 14.30 min

        boolean success = false;

        try{
            if(exchange.getRequestMethod().equalsIgnoreCase("get")){


                String urlPath = exchange.getRequestURI().toString();

                    if(urlPath == null || urlPath.equals("/") ){ //always double check the type of equals/== you are using
                        urlPath = "/index.html";

                    }

                String filePath = "web" + urlPath;

                 File file = new File(filePath);

                 if(!file.exists()){
//                     File errorFile = new File("web/HTML/404.html"); // just to send an http not found response
//                     //may just need to


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



//    If the file exists, read the file and write it to the HttpExchange’soutput stream–
//        OutputStream respBody = exchange.getResponseBody();Files.copy(file.toPath(), respBody);





//services...Will have a method for the function, create new database object, open connection, create a person object
//request, result and models are going to be shared with the client and will be able to be copied over.

//response and request will match what is given in the specs
