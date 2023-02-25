package JSonMagic.json;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class LocationsGenerator {



//    public LocationGenerator() throws FileNotFoundException {
//
//        Reader reader = new FileReader("locations.json");
//
//        Gson gson = new Gson();
//
//        LocationData locData = (LocationData) gson.fromJson(reader, LocationData.class);
//    }

    public LocationData getLocationList() throws FileNotFoundException{
        try{
            Reader reader = new FileReader("locations.json");

            Gson gson = new Gson();

            LocationData locData = (LocationData) gson.fromJson(reader, LocationData.class); //is this filling the array with location objects?

            return locData;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        }
    }
}
