package dao;

import model.Event;
import model.User;

import java.sql.*;


/**
 * User DAO for accessing user data in the database
 */

public class UserDao {

    /**
     * connection object to connect to database
     */

    private final Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    //pass in and recieve model objects back in return


    /**
     * will insert a new user into the database
     *
     * @param user new user to be inserted into the database
     */

    public void insert(User user) throws DataAccessException {

        String sql = "INSERT INTO User (username, password, email, firstName, lastName, gender, personID) VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());


            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a USER (Always double check this)  into the database");
        }


    }

    /**
     * Will return a specific user from the database
     *
     * @param personID a unique ID associated with the user in order to find it in the database
     * @return the user associated with the specified ID from the parameter
     */
    public User find(String personID) throws DataAccessException {

        User user;
        ResultSet rs;
        String sql = "SELECT * FROM User WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a User from the Database (Always double check this code) in the database");
        }

    }


    public User findUser(String userName) throws DataAccessException {

        User user;
        ResultSet rs;
        String sql = "SELECT * FROM User WHERE username = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a User from the Database (Always double check this code) in the database");
        }

    }

    /**
     * Used to determine if the given username exists in the database
     *
     * @param userName username we want to determine exists or not in the database
     * @return String of username we were looking for
     * @throws DataAccessException
     */

    public String findUsername(String userName) throws DataAccessException {

        User user;
        ResultSet rs;
        String sql = "SELECT * FROM User WHERE userName = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID"));
                return user.getUsername();
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a User from the Database (Always double check this code) in the database");
        }

    }

    /**
     * Used to find personID given a username
     *
     * @param userName username of user we want the ID for
     * @return String of personID connected to user we are concerned with
     * @throws DataAccessException
     */

    public String findPersonID(String userName) throws DataAccessException {
        User user;
        ResultSet rs;
        String sql = "SELECT * FROM User WHERE userName = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"),
                        rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("gender"), rs.getString("personID"));
                return user.getPersonID();
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a User from the Database (Always double check this code) in the database");
        }
    }

    /**
     * verifies that the given username and password are connected to one another
     *
     * @param username username we want to validate the password for
     * @param password password to check if connects to given username
     * @return true or false based on whether or not given usenrame and password relate
     * @throws DataAccessException
     */


    public boolean verifyCredentials(String username, String password) throws DataAccessException {

        ResultSet rs;
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while verifying User credentials in the database");
        }

    }

    /**
     * will delete all data from database
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM User";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the User (always double check this) table");
        }

    }


}