import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            JsonFile jsonfile = new JsonFile();
            jsonfile.createTemp();
            jsonfile.readTemp();
            JSONObject addingF = Soap.convertsCelsiusToFarenheit(jsonfile.readTemp()); //Tar ut Celelsius värdet ur arreyen och skickar till metoden.
            Rest rest = new Rest();
            rest.sumForTempC(addingF);
            rest.sumForTempF(addingF);
            rest.averageTempMethodF();
            rest.averageTempMethodC();
            rest.averageTempJsonFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


