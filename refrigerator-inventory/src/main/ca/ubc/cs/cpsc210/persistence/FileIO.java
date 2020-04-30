package ca.ubc.cs.cpsc210.persistence;

import ca.ubc.cs.cpsc210.model.RefrigeratorSystem;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

import static ca.ubc.cs.cpsc210.persistence.Jsonifier.*;

public class FileIO {
    public static final File jsonDataFile = new File("./src/refrigeratorsystem.json");
    private static RefrigeratorSystem rfs = RefrigeratorSystem.getInstance();


    // EFFECTS: attempts to read jsonDataFile and parse it
    //          returns a RefrigeratorSystem from the content of jsonDataFile
    public static void read() {
        try (FileReader reader = new FileReader("refrigeratorsystem.json")) {
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = "";
            while ((bufferedReader.read()) != -1) {
                try {
                    rfs.getRefrigeratorRecipeList().addRecipe(bufferedReader.readLine());
                } catch (EmptyStringException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: saves the state of RefrigerationSystem to jsonDataFile
    public static void write() {
        try (FileWriter writer = new FileWriter("refrigeratorsystem.json")) {
            JSONObject fridgeJson = fridgeToJson(rfs.getFridgeUnit());
            JSONObject freezerJson = freezerToJson(rfs.getFreezerUnit());
            JSONArray groceryListJson = groceryListToJson(rfs.getRefrigeratorGroceryList());
            JSONArray recipesJson = recipeListToJson(rfs.getRefrigeratorRecipeList());

            writer.write(fridgeJson.toString());
            writer.write(freezerJson.toString());
            writer.write(groceryListJson.toString());
            writer.write(recipesJson.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
