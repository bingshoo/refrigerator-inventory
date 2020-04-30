package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import ca.ubc.cs.cpsc210.model.exceptions.NotInListException;
import ca.ubc.cs.cpsc210.ui.*;

import org.junit.jupiter.api.Test;
import static ca.ubc.cs.cpsc210.persistence.Jsonifier.recipeListToJson;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.json.JSONArray;
import org.json.JSONObject;
import static ca.ubc.cs.cpsc210.parsers.RecipeListParser.parse;

public class RecipeListParserTest {
    private RecipeList testRecipeList;
    private JSONArray testRecipeListJSON;
    private String recipe1 = "Cupcakes";
    private String recipe2 = "Chili";
    private String recipe3 = "Chicken Noodle Soup";

    @BeforeEach
    void setup() throws EmptyStringException {
        testRecipeList = new RecipeList();
        testRecipeList.addRecipe(recipe1);
        testRecipeList.addRecipe(recipe2);
        testRecipeList.addRecipe(recipe3);

        testRecipeListJSON = recipeListToJson(testRecipeList);
    }

    @Test
    void testRecipeListToJsonWorkingProperly() throws EmptyStringException {
        RecipeList parsedRecipeList = parse(testRecipeListJSON);
        assertEquals(testRecipeList, parsedRecipeList);
    }

    @Test
    void testRecipeListParserOnADifferentRecipeList() throws EmptyStringException {
        RecipeList parsedRecipeList = parse(testRecipeListJSON);

        try {
            testRecipeList.removeRecipe(recipe3);
        } catch (NotInListException e) {
            fail();
        }
        testRecipeList.addRecipe("Butter Chicken");

        assertNotEquals(testRecipeList, parsedRecipeList);
        assertEquals(testRecipeList.getRecipeListSize(), parsedRecipeList.getRecipeListSize());
    }
}
