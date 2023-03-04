import java.io.*;
import java.net.*;
import java.util.Random;

import JSonMagic.json.*;
import handler.*;

import com.sun.net.httpserver.*;

/*
	This example demonstrates the basic structure of the Family Map Server
	(although it is for a fictitious "Ticket to Ride" game, not Family Map).
	The example is greatly simplfied to help you more easily understand the
	basic elements of the server.

	The Server class is the "main" class for the server (i.e., it contains the
		"main" method for the server program).
	When the server runs, all command-line arguments are passed in to Server.main.
	For this server, the only command-line argument is the port number on which
		the server should accept incoming client connections.
*/
public class Server {

    // The maximum number of waiting incoming connections to queue.
    // While this value is necessary, for our purposes it is unimportant.
    // Take CS 460 for a deeper understanding of what it means.
    private static final int MAX_WAITING_CONNECTIONS = 12;

    // Java provides an HttpServer class that can be used to embed
    // an HTTP server in any Java program.
    // Using the HttpServer class, you can easily make a Java
    // program that can receive incoming HTTP requests, and respond
    // with appropriate HTTP responses.
    // HttpServer is the class that actually implements the HTTP network
    // protocol (be glad you don't have to).
    // The "server" field contains the HttpServer instance for this program,
    // which is initialized in the "run" method below.
    private HttpServer server;

    // This method initializes and runs the server.
    // The "portNumber" parameter specifies the port number on which the
    // server should accept incoming client connections.
    private void run(String portNumber) {

        // Since the server has no "user interface", it should display "log"
        // messages containing information about its internal activities.
        // This allows a system administrator (or you) to know what is happening
        // inside the server, which can be useful for diagnosing problems
        // that may occur.
        System.out.println("Initializing HTTP Server");



        try {
            // Create a new HttpServer object.
            // Rather than calling "new" directly, we instead create
            // the object by calling the HttpServer.create static factory method.
            // Just like "new", this method returns a reference to the new object.
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Indicate that we are using the default "executor".
        // This line is necessary, but its function is unimportant for our purposes.
        server.setExecutor(null);

        // Log message indicating that the server is creating and installing
        // its HTTP handlers.
        // The HttpServer class listens for incoming HTTP requests.  When one
        // is received, it looks at the URL path inside the HTTP request, and
        // forwards the request to the handler for that URL path.
        System.out.println("Creating contexts");

        server.createContext("/", new FileHandler());

        //if we get a register request, we will forward the request to the appropriate handler
        server.createContext("/user/register", new RegisterHandler());

        server.createContext("/user/login", new LoginHandler());

        server.createContext("/clear", new ClearHandler());

        server.createContext("/fill/", new FillHandler());






        // Create and install the "default" (or "file") HTTP handler.
        // All requests that do not match the other handler URLs
        // will be passed to this handle.
        // These are requests to download a file from the server
        // (e.g., web site files)






        // Log message indicating that the HttpServer is about the start accepting
        // incoming client connections.


        System.out.println("Starting server");

        // Tells the HttpServer to start accepting incoming client connections.
        // This method call will return immediately, and the "main" method
        // for the program will also complete.
        // Even though the "main" method has completed, the program will continue
        // running because the HttpServer object we created is still running
        // in the background.
        server.start();

        // Log message indicating that the server has successfully started.
        System.out.println("Server started");
    }

    // "main" method for the server program
    // "args" should contain one command-line argument, which is the port number
    // on which the server should accept incoming client connections.
    public static void main(String[] args) throws FileNotFoundException {
        String portNumber = args[0];
        System.out.println("Starting server on port "+portNumber);
        new Server().run(portNumber);


//        femaleNameGenerator names = new femaleNameGenerator();
//
//        names.getFemaleNameList();

//        LocationsGenerator locs = new LocationsGenerator();
//
//
//
//        locs.getLocationList();
//
//        for(Location location: locs.getLocationList()){
//            System.out.println(location.getCity());
//            System.out.println(location.getLatitude());
//        }

//        maleNameGenerator maleNames = new maleNameGenerator();
//
//        maleNames.getMaleNameArray();

//        sirNameGenerator sirnames = new sirNameGenerator();
//
//        sirnames.getSireNameList();


//        maleNameGenerator maleNames = new maleNameGenerator();
//
//        int max = maleNames.getMaleNameArray().size();
//
//        Random randomFirstName = new Random();
//
//        int randomNameIndex = randomFirstName.nextInt(max+1); //arrays start at 0 in Java, right?
//
//      System.out.println(maleNames.getMaleNameArray().get(randomNameIndex));

        //getting random names is working!






    }
}

