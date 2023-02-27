package JSonMagic.json;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class LocationsGenerator {





//    public LocationGenerator() throws FileNotFoundException {
//
//        Reader reader = new FileReader("locations.json");
//
//        Gson gson = new Gson();
//
//        LocationData locData = (LocationData) gson.fromJson(reader, LocationData.class);
//    }

    public ArrayList<Location> getLocationList() throws FileNotFoundException{

        ArrayList<Location> listOfLocationObjects = new ArrayList<>();
        try{

            Reader reader = new FileReader("json/locations.json");

            Gson gson = new Gson();

            LocationData locData = (LocationData) gson.fromJson(reader, LocationData.class); //is this filling the array with location objects?



            for(Location locObject: locData.getData()){

                listOfLocationObjects.add(locObject);

            }

            return listOfLocationObjects;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        }
    }
}
