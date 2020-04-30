package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.GroceryItem;
import ca.ubc.cs.cpsc210.model.GroceryList;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import org.json.JSONArray;
import org.json.JSONObject;

public class GroceryListParser {

    // EFFECTS: parses JSONArray that represented by an input as a groceryList and returns it
    public static GroceryList parse(JSONArray input) throws NegativeInputException, EmptyStringException {
        GroceryList groceryList = new GroceryList();
        JSONArray groceryItemsArray = input;

        for (Object object: groceryItemsArray) {
            JSONObject groceryItemJson = (JSONObject) object;
            String groceryName = groceryItemJson.getString("groceryName");
            int groceryQuantity = groceryItemJson.getInt("groceryQuantity");
            groceryList.addToGroceryList(groceryName, groceryQuantity);
        }

        return groceryList;
    }
}
