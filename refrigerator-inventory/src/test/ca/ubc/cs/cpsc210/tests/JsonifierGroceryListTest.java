package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import ca.ubc.cs.cpsc210.ui.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static ca.ubc.cs.cpsc210.persistence.Jsonifier.groceryItemToJson;
import static ca.ubc.cs.cpsc210.persistence.Jsonifier.groceryListToJson;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonifierGroceryListTest {
    private GroceryList testGroceryList;
    private JSONObject testGroceryItemJSON;
    private JSONArray testGroceryListJSON;

    @BeforeEach
    void setup() throws EmptyStringException, NegativeInputException {
        testGroceryList = new GroceryList();
        testGroceryList.addToGroceryList("Carrots", 2);
        testGroceryList.addToGroceryList("Chicken Thighs", 4);
        testGroceryList.addToGroceryList("Ground Beef", 1);

        testGroceryItemJSON = new JSONObject();
        testGroceryItemJSON = groceryItemToJson(new GroceryItem("Carrots", 2));

        testGroceryListJSON = new JSONArray();
        testGroceryListJSON = groceryListToJson(testGroceryList);
    }

    @Test
    void testGroceryItemJSONConstructor() {
        assertEquals(testGroceryItemJSON.get("groceryName"), "Carrots");
        assertEquals(testGroceryItemJSON.get("groceryQuantity"), 2);
    }
    @Test
    void testGroceryItemToJson() {
        JSONObject groceryItemToCompare = null;
        try {
            groceryItemToCompare = groceryItemToJson(new GroceryItem("Carrots", 2));
        } catch (EmptyStringException e) {
            fail();
        } catch (NegativeInputException e) {
            fail();
        }
        assertTrue(testGroceryItemJSON.similar(groceryItemToCompare));
        assertEquals(testGroceryItemJSON.get("groceryName"), groceryItemToCompare.get("groceryName"));
        assertEquals(testGroceryItemJSON.get("groceryQuantity"), groceryItemToCompare.get("groceryQuantity"));
    }

    @Test
    void testGroceryItemToJsonComparingTwoDifferentJsonObjects() {
        JSONObject groceryItemToCompare = null;
        try {
            groceryItemToCompare = groceryItemToJson(new GroceryItem("Apple", 3));
        } catch (EmptyStringException e) {
            fail();
        } catch (NegativeInputException e) {
            fail();
        }
        assertFalse(testGroceryItemJSON.similar(groceryItemToCompare));
        assertNotEquals(testGroceryItemJSON.get("groceryName"), groceryItemToCompare.get("groceryName"));
        assertNotEquals(testGroceryItemJSON.get("groceryQuantity"), groceryItemToCompare.get("groceryQuantity"));
    }

    @Test
    void testGroceryItemToJsonComparingTwoJsonObjectsWithSameGroceryName() {
        JSONObject groceryItemToCompare = null;
        try {
            groceryItemToCompare = groceryItemToJson(new GroceryItem("Carrots", 3));
        } catch (EmptyStringException e) {
            fail();
        } catch (NegativeInputException e) {
            fail();
        }
        assertFalse(testGroceryItemJSON.similar(groceryItemToCompare));
        assertEquals(testGroceryItemJSON.get("groceryName"), groceryItemToCompare.get("groceryName"));
        assertNotEquals(testGroceryItemJSON.get("groceryQuantity"), groceryItemToCompare.get("groceryQuantity"));
    }

    @Test
    void testGroceryItemToJsonComparingTwoJsonObjectsWithSameGroceryQuantity() {
        JSONObject groceryItemToCompare = null;
        try {
            groceryItemToCompare = groceryItemToJson(new GroceryItem("Apples", 2));
        } catch (EmptyStringException e) {
            fail();
        } catch (NegativeInputException e) {
            fail();
        }
        assertFalse(testGroceryItemJSON.similar(groceryItemToCompare));
        assertNotEquals(testGroceryItemJSON.get("groceryName"), groceryItemToCompare.get("groceryName"));
        assertEquals(testGroceryItemJSON.get("groceryQuantity"), groceryItemToCompare.get("groceryQuantity"));
    }

    @Test
    void testGroceryListJSONConstructor() throws EmptyStringException, NegativeInputException {
        JSONObject groceryItemToCompare1 =
                groceryItemToJson(new GroceryItem("Carrots", 2));
        JSONObject groceryItemToCompare2 =
                groceryItemToJson(new GroceryItem("Chicken Thighs", 4));
        JSONObject groceryItemToCompare3 =
                groceryItemToJson(new GroceryItem("Ground Beef", 1));
        JSONArray groceryListJsonToCompare = groceryListToJson(testGroceryList);

        assertTrue(testGroceryListJSON.getJSONObject(0).similar(groceryItemToCompare1));
        assertTrue(testGroceryListJSON.getJSONObject(1).similar(groceryItemToCompare2));
        assertTrue(testGroceryListJSON.getJSONObject(2).similar(groceryItemToCompare3));
        assertTrue(testGroceryListJSON.similar(groceryListJsonToCompare));
    }

    @Test
    void testGroceryListJSONWhenComparingNonSimilarOnes() throws EmptyStringException, NegativeInputException {
        JSONObject groceryItemToCompare1 =
                groceryItemToJson(new GroceryItem("Apples", 2));
        JSONObject groceryItemToCompare2 =
                groceryItemToJson(new GroceryItem("Chicken Thighs", 4));
        JSONObject groceryItemToCompare3 =
                groceryItemToJson(new GroceryItem("Ground Beef", 1));
        JSONArray groceryListJsonToCompare = groceryListToJson(testGroceryList);
        groceryListJsonToCompare.put(groceryItemToCompare1);

        assertFalse(testGroceryListJSON.similar(groceryListJsonToCompare));
    }
}