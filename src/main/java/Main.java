import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        try {
            JsonFile jsonfile = new JsonFile("1", "2", "3", "4", "5", "6", "7");
            jsonfile.createTemp();
            //jsonfile.readTemp();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
