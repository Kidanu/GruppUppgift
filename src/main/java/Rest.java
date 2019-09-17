import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;


class Rest {

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
            Main.jsonfileName = scan.nextLine();
            FileWriter file = new FileWriter(Main.jsonfileName + ".json");
            Main.jsonFileNameArray.add(Main.jsonfileName);
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

        ArrayList<Integer> medianListC = new ArrayList<Integer>();
        ArrayList<Integer> medianListF = new ArrayList<Integer>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject arrayObjectRandom = array.getJSONObject(i);
            int C = (Integer) arrayObjectRandom.get("C");
            medianListC.add(C);
            String F = (String) arrayObjectRandom.get("F");
            double d = Double.valueOf(F);
            int value = (int) d;
            medianListF.add((value));
        }
        Collections.sort(medianListC);
        int middleSlotC = medianListC.size();
        int medianValueC = 0;
        if (middleSlotC % 2 == 1) {
            int odd = (middleSlotC / 2) + 1;
            medianValueC = medianListC.get(odd - 1);
        } else {
            int notOdd = middleSlotC / 2;
            int K = medianListC.get(notOdd);
            medianValueC = (K + K) / 2;
        }
        String strC = String.valueOf(medianValueC);
        newObject.put("Celsius Median", strC);
//----------------------------------------------
        Collections.sort(medianListF);
        int middleSlotF = medianListF.size();
        int medianValueF = 0;
        if (middleSlotF % 2 == 1) {
            int odd = (middleSlotF / 2) + 1;
            medianValueF = medianListF.get(odd - 1);
        } else {
            int notOdd = middleSlotF / 2;
            int K = medianListF.get(Math.round(notOdd));
            medianValueF = ((K + K) / 2);
        }
        String strF = String.valueOf(medianValueF);
        newObject.put("FarebgeutMedian", strF);

        try {
            System.out.println("Skriv namn på Median temperatur filen");
            Main.jsonfileName = scan.nextLine();
            FileWriter file = new FileWriter(Main.jsonfileName + ".json");
            Main.jsonFileNameArray.add(Main.jsonfileName);
            file.write(newObject.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newObject;
    }

}