import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.soap.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Soap {
    Main main = new Main();
    String xmlUrl = "https://www.w3schools.com/xml/tempconvert.asmx?WSDL";
    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

    }

    //Här använder vi värdet Celius från JSonfilen med arrayen och hämtar ut farenheit värden med hjälp utav callSoapMetoden
    public JSONObject convertsCelsiusToFarenheit(JSONObject jsonCelsius) throws Exception {
        JSONArray array = jsonCelsius.getJSONArray("Temperatur");
        JSONObject arrayObjekt = null;
        try {
            for (int i = 0; i < array.length(); i++) {
                arrayObjekt = array.getJSONObject(i);
                String svarTemp = arrayObjekt.get("C").toString();
                String farenheit = callSoapWebService(svarTemp); //Här stoppar vi in svaret utav det vi får från callSoapMetoden
                arrayObjekt.put("F", farenheit);
            }


            System.out.println("Skriv namn på jsonfil som får farenheitvärden i");
            main.jsonfileName = scan.nextLine();
            FileWriter file = new FileWriter(main.jsonfileName + ".json"); //Skapar en ny jsonfil med även Farenheit värden i.
            main.jsonFileNameArray.add(main.jsonfileName);
            file.write(jsonCelsius.toString());
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayObjekt;
    }

    private String callSoapWebService(String Celsius) throws Exception {
        String xmlUrl = "https://www.w3schools.com/xml/tempconvert.asmx?WSDL";

        MessageFactory messageFactory = MessageFactory.newInstance();//Skapar mallen till SoapMessage, SoapEnvelope, SoapPart och SoapHeader.
        SOAPMessage soapMessages = messageFactory.createMessage();

        //Börjar bygga meddelandet till XML:et
        SOAPPart soapPart = soapMessages.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addNamespaceDeclaration("ns1", "https://www.w3schools.com/xml/");//va
        SOAPBody soapBody = soapEnvelope.getBody();
        SOAPElement soapElement = soapBody.addChildElement("CelsiusToFahrenheit", "ns1");
        SOAPElement soapElement2 = soapElement.addChildElement("Celsius", "ns1");
        soapElement2.addTextNode(Celsius);//Skriver in Celsius graden mellan taggarna i xml funktionen.

        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        SOAPMessage soapResponse = soapConnection.call(soapMessages, xmlUrl);//Hämtar ut hela bodyn med xml-kod där värdet farenheit finns.
        //System.out.println(soapResponse.getSOAPBody().getTextContent());

        return soapResponse.getSOAPBody().getTextContent(); // Hämtar ut bara värdet Farenheit från hela bodyn.
    }

}