
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Scanner;


public class Rest {

    public static void main(String[] args) throws IOException {

}
    int antalC = 0;
    int antalF = 0;
    int cValues = 0;
    int fValues = 0;
    String averageF = "";
    String averageC = "";

    /*public Rest(int antalC, int antalF, int cValues, int fValues) {
        antalC = this.antalC;
        antalF = this.antalF;
        cValues = this.cValues;
        fValues = this.fValues;
    }*/

    public void sumForTempC(JSONObject jsonObject) {//Räknar ut summan för all Celsius grader
        JsonFile file = new JsonFile();
        JSONArray array = jsonObject.getJSONArray("Temperatur");
        for (int i = 0; i < array.length(); i++) {
            JSONObject arrayObjekt = array.getJSONObject(i);
            cValues += (Integer) arrayObjekt.get("C");
            antalC = i;
        }
        System.out.println(antalC);
    }

    public void sumForTempF(JSONObject jsonObject) {                                //Räknar ut summan för alla farenheit grader
        JSONArray array = jsonObject.getJSONArray("Temperatur");
        for (int i = 0; i < array.length(); i++) {
            JSONObject arrayObjekt = array.getJSONObject(i);
            fValues += (Integer) arrayObjekt.get("F");
            antalF += i;
        }
    }

    public void  averageTempMethodF () {                                            //Skickar rest api för att räkna ut medelvärdet på Farenheit
        {
            try {
                URL url = new URL("https://api.mathjs.org/v4/?expr=" + this.fValues + "%2F" + this.antalF + "");
                HttpsURLConnection con = null;
                con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                String result = "";
                int responseCode = con.getResponseCode();
                if (responseCode == 200) {
                    Scanner scanner = new Scanner(con.getInputStream());
                    while (scanner.hasNextLine()) {
                        averageF += scanner.nextLine();
                    }
                    scanner.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(averageF);
        }
    }

    public void averageTempMethodC() {                                                  //Skickar rest api för att räkna ut medevärden på Celsius
        {
            try {
                URL url = new URL("https://api.mathjs.org/v4/?expr=" + this.cValues + "%2F" + this.antalC + "");
                HttpsURLConnection con = null;
                con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                String result = "";
                int responseCode = con.getResponseCode();
                if (responseCode == 200) {
                    Scanner scanner = new Scanner(con.getInputStream());
                    while (scanner.hasNextLine()) {
                        averageC += scanner.nextLine();
                    }
                    scanner.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public JSONObject averageTempJsonFile(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Celsius medel", averageC);
        jsonObject.put("Farenheit medel", averageF);
        try {
            FileWriter file = new FileWriter("AverageTemp.json");
            file.write(jsonObject.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
