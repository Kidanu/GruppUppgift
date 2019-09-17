import jdk.nashorn.internal.ir.WhileNode;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String jsonfileName;
    static List<String> jsonFileNameArray = new ArrayList<String>();

    public static void main(String[] args) {
        JsonFile jsonFile = new JsonFile();
        Soap soap = new Soap();
        Rest rest = new Rest();
        char quit = 'n';
        String input;
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        try {
            while (quit != 'Y') {
                System.out.println("1: Skapa JsonFil med Celsius värden i"
                        + "\n2: Hämta Farenheit värden för en specifik JsonFil"
                        + "\n3: Beräkna ut medelvärden för både C och F i specifik JsonFil"
                        + "\n4: Beräkna ut median värdet för specifik JsonFil"
                        + "\n5: Titta vad som finns i filerna"
                        + "\n6: Avsluta programmet"
                        + "\n-------------------------"
                        + "\n Välj mellan alternativen:");
                choice = scan.nextInt();

                switch (choice) {
                    case 1:
                        jsonFile.createTemp();
                        System.out.println("-------------------------\n");
                        break;
                    case 2:
                        jsonFile.printObjects();
                        JSONObject F = soap.convertsCelsiusToFarenheit(jsonFile.readTemp()); //Måste sätta dit en variabel för att den ska kunn add:as till jsonFileArray variablen.
                        System.out.println("-------------------------\n");
                        break;
                    case 3:
                        jsonFile.printObjects();
                        rest.sumForTempCF(jsonFile.readTemp());
                        JSONObject average = rest.averageTempJsonFile();

                        System.out.println("-------------------------\n");
                        break;
                    case 4:
                        jsonFile.printObjects();
                        rest.medianTempJsonFile(jsonFile.readTemp());
                        System.out.println("-------------------------\n");
                        break;
                    case 5:
                        jsonFile.printObjectsSeparate();
                        System.out.println("-------------------------\n");
                        break;
                    case 6:
                        quit = 'Y';
                        break;
                    default:
                        System.out.println("Välj något i menyn");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

