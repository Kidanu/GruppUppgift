import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;

class JsonFile {

    String one;
    String two;
    String three;
    String four;
    String five;
    String six;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    String seven;


    public JsonFile(String one, String two, String three, String four, String five, String six, String seven) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.six = six;
        this.seven = seven;
    }

    public JSONObject createTemp() {
        //JsonFile jsonValues = new JsonFile(one, two, three, four, five, six, seven);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(this.one);
        jsonArray.put(this.two);
        jsonArray.put(this.three);
        jsonArray.put(this.four);
        jsonArray.put(this.five);
        jsonArray.put(this.six);
        jsonArray.put(this.seven);

        jsonObject.put("Celsius", jsonArray);


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
        //System.out.println(createTemp().get("Celsius");
        //createTemp()
        //System.out.println(jsonParser.parse(reader));
        return jsonParser.parse(reader);



            /*JSONParser jsonParser = new JSONParser();
            BufferedReader br = null;

            //FileReader fileReader = new FileReader("Temp.json");
            //Object object = jsonParser.parse(fileReader);

              /*  Object object = jsonParser.parse(new FileReader("Temp.json"));
                String random = object.toString();
                JSONObject jsonObject = (JSONObject) object;
                JSONArray companyList = (JSONArray) jsonObject.getJSONArray("Celsius");
                System.out.println(companyList);
                String inline = " ";

                Iterator<Object> iterator = companyList.iterator();
                while (iterator.hasNext()) {

                    System.out.println(iterator.toString());
               }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }*/
    }

}
