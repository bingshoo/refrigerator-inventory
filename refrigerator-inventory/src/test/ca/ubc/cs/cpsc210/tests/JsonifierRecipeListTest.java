package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;

import org.junit.jupiter.api.Test;

import static ca.ubc.cs.cpsc210.persistence.Jsonifier.recipeListToJson;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonifierRecipeListTest {
    private RecipeList testRecipeList;
    private JSONArray testRecipeListJson;
    private String recipe1 = "Cupcakes";
    private String recipe2 = "Chili";
    private String recipe3 = "Chicken Noodle Soup";

    @BeforeEach
    void setup() throws EmptyStringException {
        testRecipeList = new RecipeList();
        testRecipeList.addRecipe(recipe1);
        testRecipeList.addRecipe(recipe2);
        testRecipeList.addRecipe(recipe3);
    }

    @Test
    void testRecipeListToJson() {
        testRecipeListJson = recipeListToJson(testRecipeList);

        JSONArray similarRecipeListArray = new JSONArray();
        similarRecipeListArray.put("Cupcakes");
        similarRecipeListArray.put("Chili");
        similarRecipeListArray.put("Chicken Noodle Soup");

        assertTrue(testRecipeListJson.similar(similarRecipeListArray));
        System.out.println(testRecipeListJson.toString());
    }
}
