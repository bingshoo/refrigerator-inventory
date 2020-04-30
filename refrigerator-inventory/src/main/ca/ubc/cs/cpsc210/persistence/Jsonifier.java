package ca.ubc.cs.cpsc210.persistence;

import ca.ubc.cs.cpsc210.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Jsonifier {

    // EFFECTS: returns a JSONObject representing a grocery item
    public static JSONObject groceryItemToJson(GroceryItem g) {
        JSONObject groceryItemJson = new JSONObject();

        groceryItemJson.put("groceryName", g.getGroceryName());
        groceryItemJson.put("groceryQuantity", g.getGroceryQuantity());
        return groceryItemJson;
    }

    // EFFECTS: returns a JSONArray representing a grocery list
    public static JSONArray groceryListToJson(GroceryList gl) {
        JSONArray groceryListArray = new JSONArray();

        for (GroceryItem g: gl.getGroceryList()) {
            groceryListArray.put(groceryItemToJson(g));
        }
        return groceryListArray;
    }

    // EFFECTS: returns a JSONArray representing a recipe list
    public static JSONArray recipeListToJson(RecipeList rl) {
        JSONArray recipeListArray = new JSONArray();

        for (String r: rl.getRecipeList()) {
            recipeListArray.put(r);
        }
        return recipeListArray;
    }

    // EFFECTS: returns a JSONObject representing an item
    private static JSONObject itemToJson(Item item) {
        JSONObject itemJson = new JSONObject();

        itemJson.put("itemName", item.getItemName());
        itemJson.put("expirationDate", item.getExpirationDate());
        itemJson.put("dateBought", item.getDateBought());
        itemJson.put("weight", item.getWeight());
        itemJson.put("cost", item.getCost());
        itemJson.put("goesInFreezer", item.isGoesInFreezer());

        return itemJson;
    }

    // EFFECTS: returns a JSONArray representing a list of items
    private static JSONArray itemsToArray(Freezer freezer) {
        JSONArray itemsArray = new JSONArray();

        for (Object i: freezer.getFreezerItems()) {
            Item item = (Item) i;
            itemsArray.put(itemToJson(item));
        }
        return itemsArray;
    }

    // EFFECTS: returns a JSONObject that represents a freezer
    public static JSONObject freezerToJson(Freezer freezer) {
        JSONObject freezerJson = new JSONObject();

        freezerJson.put("freezerItems", itemsToArray(freezer));
        freezerJson.put("numItems", freezer.getNumItems());

        return freezerJson;
    }

    // EFFECTS: returns a JSONArray representing a list of fridge items
    private static JSONArray fridgeItemsToArray(Fridge fridge) {
        JSONArray itemsArray = new JSONArray();

        for (Object i: fridge.getFridgeItems()) {
            Item item = (Item) i;
            itemsArray.put(itemToJson(item));
        }
        return itemsArray;
    }

    // EFFECTS: returns a JSONObject that represents a fridge
    public static JSONObject fridgeToJson(Fridge fridge) {
        JSONObject fridgeJson = new JSONObject();

        fridgeJson.put("fridgeItems", fridgeItemsToArray(fridge));
        fridgeJson.put("numItems", fridge.getNumItems());

        return fridgeJson;
    }
}
