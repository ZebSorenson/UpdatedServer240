package RequestResult;


/**
 * Result containing info to send back to handler whether or not service is successful
 */

public class eventIDResult {

    /**
     * associatedUsername returned after eventID request
     */

    String associatedUsername;

    /**
     * eventID returned after eventID request
     */

    String eventID;

    /**
     * personID returned after eventID request
     */

    String personID;

    /**
     * latitude returned after eventID request
     */

    Float latitude;

    /**
     * longitude returned after eventID request
     */

    Float longitude;

    /**
     * country returned after eventID request
     */

    String country;

    /**
     * city returned after eventID request
     */


    String city;

    /**
     * eventType returned after eventID request
     */

    String eventType;

    /**
     * year returned after eventID request
     */

    Integer year;

    /**
     * success returned after eventID request
     */

    Boolean success;

    //error, will also use success

    /**
     * message returned after eventID request
     */

    String message;
}