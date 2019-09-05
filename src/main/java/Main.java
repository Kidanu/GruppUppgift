import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        try {
            JsonFile jsonfile = new JsonFile();
            jsonfile.createTemp();
            jsonfile.readTemp();
            JSONObject addingF = Soap.convertsCelsiusToFarenheit(jsonfile.readTemp()); //Tar ut Celelsius värdet ur arreyen och skickar till metoden.
            //System.out.println(addingF);
            Rest rest = new Rest();
            rest.sumForTempC(jsonfile.readTemp());
            System.out.println("antal cel");



            try {
                FileWriter file = new FileWriter("Nya_filen.json", true); //Skapar en ny jsonfil med även Farenheit värden i.
                file.write(addingF.toString());

                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
