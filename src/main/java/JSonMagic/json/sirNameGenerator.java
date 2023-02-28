package JSonMagic.json;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class sirNameGenerator {

    ArrayList<String> sirNameArray = new ArrayList<>();


    public ArrayList<String> getSireNameList() throws FileNotFoundException {
        try{
            Reader reader = new FileReader("json/snames.json");

            Gson gson = new Gson();

            sirNameData snameList = (sirNameData) gson.fromJson(reader, sirNameData.class); //is this filling the array with location objects?


            for(String sname: snameList.getData()){

                sirNameArray.add(sname);

            }

            System.out.println(sirNameArray);

            return sirNameArray;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        }
    }


}
