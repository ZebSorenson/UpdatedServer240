package dao;

import model.Authtoken;

import java.sql.*;

/**
 * A unique token for each user everytime they log in. Used for authentication purposes to be stored in the database
 */
public class AuthtokenDao {

    private final Connection conn;

    public AuthtokenDao(Connection conn) {this.conn = conn;}


    public void insert(Authtoken authtoken) throws SQLException, DataAccessException {
        String sql = "INSERT INTO  AuthorizationToken (authtoken, username) VALUES (?, ?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());

            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered when inserting an AUTHTOKEN (Always double check this into the database ");
        }


    }

    //will deffinitely need find functions but can write them later when you see a need for what needs to be passed into them

    public void clear() throws  DataAccessException{
        String sql = "DELETE FROM AuthorizationToken";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the AuthorizationToken table");
        }
    }

//end of class
}