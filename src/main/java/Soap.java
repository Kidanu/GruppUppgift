import org.json.JSONArray;
import org.json.JSONObject;
import sun.plugin2.message.Message;

import javax.xml.soap.*;
import java.io.FileWriter;
import java.io.IOException;

public class Soap {
    String xmlUrl = "https://www.w3schools.com/xml/tempconvert.asmx?WSDL";

    public static void main(String[] args) throws Exception {
        //String svar = callSoapWebService("25");
    }

    //Här använder vi värdet Celius från JSonfilen med arrayen och hämtar ut farenheit värden med hjälp utav callSoapMetoden
    public static JSONObject convertsCelsiusToFarenheit(JSONObject jsonCelsius) throws Exception {
        JSONArray array = jsonCelsius.getJSONArray("Temperatur");

        for (int i = 0; i < array.length(); i++) {
            JSONObject arrayObjekt = array.getJSONObject(i);
            String svarTemp = arrayObjekt.get("C").toString();
            String farenheit = callSoapWebService(svarTemp);
            arrayObjekt.put("F", farenheit);
        }
        try {
            FileWriter file = new FileWriter("Nya_filen.json", true); //Skapar en ny jsonfil med även Farenheit värden i.
            file.write(jsonCelsius.toString());

            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonCelsius;
    }


    private static String callSoapWebService(String Celsius) throws Exception {
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
        soapElement2.addTextNode(Celsius);

        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance(); // För att skapa connection måste finnas en ny instance.
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
        SOAPMessage soapResponse = soapConnection.call(soapMessages, xmlUrl);//Hämtar ut hela bodyn med xml-kod där värdet farenheit finns.
        //System.out.println(soapResponse.getSOAPBody().getTextContent());

        return soapResponse.getSOAPBody().getTextContent(); // Hämtar ut bara värdet Farenheit från hela bodyn.
    }

}
