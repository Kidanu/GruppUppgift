import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;


class JsonFile {

    public JsonFile() {
    }

    //Denna metod skapar Json fil med en array med 20 olika random grader.
    public JSONObject createTemp() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        //Sätter temperatur värden i en array
        jsonObject.put("Temperatur", jsonArray);
        for (int i = 0; i < 3; i++) {
            int rnd;
            int minimum = -40;
            int maximum = 40;
            rnd = minimum + (int) (Math.random() * (maximum - minimum));
            jsonArray.put(new JSONObject().put("C", rnd).put("F", "")); //Sättder ditt tomt värde för att sätta in sen i.
        }
        //Skapar Json filen med värden
        try {
            FileWriter file = new FileWriter("Temp.json");
            file.write(jsonObject.toString());
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject readTemp() throws Exception { //Kör denna metod för att läsa av jsonfilen efter den har skapats.
        File file = new File("Temp.json");
        String content = FileUtils.readFileToString(file, "utf-8");
        return new JSONObject(content);
    }

}
