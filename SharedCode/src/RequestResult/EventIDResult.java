package RequestResult;


/**
 * Result containing info to send back to handler whether or not service is successful
 */

public class EventIDResult {

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

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}