import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class JsonFile {
    int random = 5;
    Main main = new Main();
    //String jsonFileName;
    Scanner scan = new Scanner(System.in);

    //Denna metod skapar Json fil med en array med 20 olika random grader.
    public JSONObject createTemp() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();


        int antalTemp = 0; //Används i for loopen

        System.out.println("Skriv in antal Celsius värden du vill ha");
        antalTemp = scan.nextInt();
        //Sätter temperatur värden i en array
        jsonObject.put("Temperatur", jsonArray);
        for (int i = 0; i < antalTemp; i++) {
            int rnd;
            int minimum = -20;
            int maximum = 40;
            rnd = minimum + (int) (Math.random() * (maximum - minimum));
            jsonArray.put(new JSONObject().put("C", rnd).put("F", "")); //Sättder ditt tomt värde för att sätta in sen i.
        }
        //Skapar Json filen med värden
        try {
            System.out.println("Välj namn på json filen");
            scan.nextLine();
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

    public JSONObject readTemp() throws Exception { //Kör denna metod för att läsa av jsonfilen efter den har skapats.

        System.out.println("Vilken JsonFil vill du skriva ut");
        main.jsonfileName = scan.nextLine();
        File file = new File(main.jsonfileName + ".json");
        String content = FileUtils.readFileToString(file, "utf-8");
        return new JSONObject(content);

    }

    public void printObjects() {
        System.out.println("Vilken fil vill du använda dig utav?");
        System.out.println(main.jsonFileNameArray + "\n");
    }

    public void printObjectsSeparate() throws IOException {


        System.out.println("Vilken JsonFil vill du skriva ut");
        System.out.println(main.jsonFileNameArray + "\n");
        main.jsonfileName = scan.nextLine();
        File file = new File(main.jsonfileName + ".json");
        String content = FileUtils.readFileToString(file, "utf-8");
        System.out.println(content);
        //return new JSONObject(content);

       /* System.out.println("Vilkfen fil vill du titta igenom?");
        for (int i = 0;i < main.jsonFileNameArray.size();i++){
            System.out.println(main.jsonFileNameArray.get(i) + i);
        }
        int siffra = scan.nextInt();
        System.out.println(main.jsonFileNameArray.g);*/
    }
}