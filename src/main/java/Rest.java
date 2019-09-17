import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;


class Rest {

    public static void main(String[] args) throws IOException {

    }

    Main main = new Main();
    Scanner scan = new Scanner(System.in);

    int antalC = 0;
    int antalF = 0;
    int cValues = 0;
    double fValues = 0;
    String averageF = "";
    String averageC = "";

    //Räknar ut summan för all Celsius och Farenheit grader
    public void sumForTempCF(JSONObject jsonObject) {
        //JsonFile file = new JsonFile();
        JSONArray array = jsonObject.getJSONArray("Temperatur");
        for (int i = 0; i < array.length(); i++) {
            JSONObject arrayObjekt = array.getJSONObject(i);
            cValues += (Integer) arrayObjekt.get("C");
            fValues += Double.parseDouble((String) arrayObjekt.get("F"));
        }
        antalC = array.length();
        antalF = array.length();
    }

    //Skickar rest api för att räkna ut medelvärdet på Farenheit
    public String averageTempMethodF() {
        {
            try {
                URL url = new URL("https://api.mathjs.org/v4/?expr=" + this.fValues + "%2F" + this.antalF + "");
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
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
            return averageF;
        }
    }

    //Skickar rest api för att räkna ut medevärden på Celsius
    public String averageTempMethodC() {
        {
            try {
                URL url = new URL("https://api.mathjs.org/v4/?expr=" + this.cValues + "%2F" + this.antalC + "");
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                String result = "";
                int responseCode = con.getResponseCode();
                if (responseCode == 200) {
                    Scanner scanner = new Scanner(con.getInputStream());
                    while (scanner.hasNextLine()) {
                        averageC += scanner.nextLine();
                    }
                    scanner.close();
                    System.out.println(averageC);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return averageC;
        }
    }

    //Skriver ut medeltemp i en ny fil
    public JSONObject averageTempJsonFile() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Celsius medel", averageTempMethodC());
        jsonObject.put("Farenheit medel", averageTempMethodF());

        try {
            System.out.println("Skriv namn på Average temperatur filen");
            main.jsonfileName = scan.nextLine();
            FileWriter file = new FileWriter(main.jsonfileName + ".json");
            main.jsonFileNameArray.add(main.jsonfileName);
            file.write(jsonObject.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    //Ska räkna ut median och skriva ut ny jsonfil.
    public JSONObject medianTempJsonFile(JSONObject jsonObject) {
        JSONArray array = jsonObject.getJSONArray("Temperatur");
        JSONObject newObject = new JSONObject();

        List<Integer> medianListC = new ArrayList<Integer>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject arrayObjectRandom = array.getJSONObject(i);
            int C = (Integer) arrayObjectRandom.get("C");
            medianListC.add(C);
        }
        List<Double> medianListF = new ArrayList<Double>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject arrayObjectRandom = array.getJSONObject(i);
            int C = (Integer) arrayObjectRandom.get("C");
            medianListC.add(C);
        }
        Collections.sort(medianListC);
        int middleSlotC = array.length() / 2;
        double medianValueC = 0;

        Collections.sort(medianListF);
        int middleSlotF = array.length() / 2;
        double medianValueF = 0;

        if (medianListC.size() % 2 == 1) {
            medianValueC = medianListC.get(middleSlotC);
        } else {
            int K = medianListC.get(middleSlotC);
            medianValueC = ((K - 1) + K) / 2;
        }

        if (medianListF.size() % 2 == 1) {
            medianValueF = medianListF.get(middleSlotF);
        } else {
            int K = medianListC.get(middleSlotC);
            medianValueF = ((K - 1) + K) / 2;
        }

        String strC = String.valueOf(medianValueC);
        newObject.put("Celsius Median", strC);

        String strF = String.valueOf(medianValueF);
        newObject.put("FarebgeutMedian", strF);

        try {
            System.out.println("Skriv namn på Median temperatur filen");
            main.jsonfileName = scan.nextLine();
            FileWriter file = new FileWriter(main.jsonfileName + ".json");
            main.jsonFileNameArray.add(main.jsonfileName);
            file.write(newObject.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newObject;
    }

}