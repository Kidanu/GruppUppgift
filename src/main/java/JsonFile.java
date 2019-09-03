import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

class JsonFile {


    public JsonFile(String one, String two, String three, String four, String five, String six, String seven) {
    }

    //Denna metod skapar Json fil med en array med 20 olika random grader.
    public JSONObject createTemp() {
        //JsonFile jsonValues = new JsonFile(one, two, three, four, five, six, seven);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        //Sätter temperatur värden i en array
        jsonObject.put("Temperatur", jsonArray);
        for (int i = 0; i < 20; i++) {
            int rnd;
            int minimum = -40;
            int maximum = 40;
            rnd = minimum + (int) (Math.random() * (maximum - minimum));
            jsonArray.put(new JSONObject().put("C", rnd).put("F", ""));
        }

        try {
            FileWriter file = new FileWriter("Temp.json");
            file.write(jsonObject.toString());
            file.close();
            //System.out.println("Detta har skrivits till filen: \n" + jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public Object readTemp() throws Exception { //Detta ska användas sen för att läsa till hemsidan.
        FileReader reader = new FileReader("Temp.json");
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray2 = createTemp().getJSONArray("Temperatur");
        for (Object s : jsonArray2) {
            System.out.println(s.toString());
        }
        System.out.println(jsonParser.parse(reader));
        return jsonParser.parse(reader);

    }

}
