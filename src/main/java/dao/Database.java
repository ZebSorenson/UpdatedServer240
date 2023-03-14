package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Databse for FMS data to be held
 */

public class Database {
    /**
     * Connection object for each time we connect to database
     */
    private Connection conn;

    // Whenever we want to make a change to our database we will have to open a connection and use
    // Statements created by that connection to initiate transactions

    /**
     * each time we need to connect to database, we will create a new connection in order to perform transactions
     * @return new connection object for this specific time we connect to database
     * @throws DataAccessException if we encounter an error when connecting to database. We will throw a DataAccessException
     * Which is a custom exception we have written
     */
    public Connection openConnection() throws DataAccessException {
        try {
            // The Structure for this Connection is driver:language:path
            // The path assumes you start in the root of your project unless given a full file path
            final String CONNECTION_URL = "jdbc:sqlite:tableStatements.db"; //is this correct? 

            // Open a database connection to the file given in the path
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }

        return conn;
    }

    /**
     * This will return the current connection.
     * If the current connection is null, it will open a new one
     * @return the current connection, if null will return the openConnection function (which creates a new connection and returns it)
     * @throws DataAccessException if in attempting to create the new connection, we will throw the custom DataAccessException
     */

    public Connection getConnection() throws DataAccessException {
        if (conn == null) {
            return openConnection();
        } else {
            return conn;
        }
    }

    // When we are done manipulating the database it is important to close the connection. This will
    // end the transaction and allow us to either commit our changes to the database (if true is passed in)
    // or rollback any changes that were made before we encountered a potential error (if false is passed in).

    // IMPORTANT: IF YOU FAIL TO CLOSE A CONNECTION AND TRY TO REOPEN THE DATABASE THIS WILL CAUSE THE
    // DATABASE TO LOCK. YOUR CODE MUST ALWAYS CLOSE THE DATABASE NO MATTER WHAT ERRORS
    // OR PROBLEMS ARE ENCOUNTERED

    /**
     * it is important that the connection is closed after use.
     * @param commit this wil inform if is yes or no we want to add the changes to the database
     */
    public void closeConnection(boolean commit) {
        try {
            if (commit) {
                // This will commit the changes to the database
                conn.commit();
            } else {
                // If we find out something went wrong, pass a false into closeConnection and this
                // will rollback any changes we made during this connection
                conn.rollback();
            }
            conn.close();
            conn = null;
        } catch (SQLException e) {
            // If you get here there are probably issues with your code and/or a connection is being left open
            e.printStackTrace();
        }
    }


}

