package JSonMagic.json;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class fnameGenerator {


    public fnamesData getFemaleNameList() throws FileNotFoundException {
        try{
            Reader reader = new FileReader("fnames.json");

            Gson gson = new Gson();

            fnamesData fnameList = (fnamesData) gson.fromJson(reader, fnamesData.class); //is this filling the array with location objects?

            return fnameList;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        }
    }
}
