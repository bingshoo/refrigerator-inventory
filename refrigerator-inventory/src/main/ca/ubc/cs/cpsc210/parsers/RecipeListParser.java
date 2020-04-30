package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.RecipeList;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeListParser {

    // EFFECTS: parses JSONArray that is represented by an input as a recipeList and returns it
    public static RecipeList parse(JSONArray input) throws EmptyStringException {
        RecipeList recipeList = new RecipeList();
        JSONArray recipeListArray = input;

        for (Object object: recipeListArray) {
            String recipe = (String) object;
            recipeList.addRecipe(recipe);
        }

        return recipeList;
    }
}
