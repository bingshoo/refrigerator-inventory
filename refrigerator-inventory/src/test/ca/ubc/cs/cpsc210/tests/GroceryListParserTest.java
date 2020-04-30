package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import ca.ubc.cs.cpsc210.ui.*;

import org.junit.jupiter.api.Test;
import static ca.ubc.cs.cpsc210.persistence.Jsonifier.groceryItemToJson;
import static ca.ubc.cs.cpsc210.persistence.Jsonifier.groceryListToJson;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.json.JSONArray;
import org.json.JSONObject;
import static ca.ubc.cs.cpsc210.parsers.GroceryListParser.parse;

public class GroceryListParserTest {
    private GroceryList testGroceryList;
    private JSONArray testGroceryListJSON = new JSONArray();
    private JSONObject testGroceryItemJSON = new JSONObject();

    @BeforeEach
    void setup() throws EmptyStringException, NegativeInputException {
        testGroceryList = new GroceryList();
        testGroceryList.addToGroceryList("Carrots", 2);
        testGroceryList.addToGroceryList("Chicken Thighs", 4);
        testGroceryList.addToGroceryList("Ground Beef", 1);

        testGroceryItemJSON = groceryItemToJson(new GroceryItem("Carrots", 2));

        testGroceryListJSON = groceryListToJson(testGroceryList);
    }

    @Test
    void testComparingParseAndCorrectGroceryList() throws NegativeInputException, EmptyStringException {
        GroceryList parsedGroceryList = parse(testGroceryListJSON);
        parsedGroceryList.printGroceryList();
        testGroceryList.printGroceryList();

        assertEquals(parsedGroceryList.groceryListToString(), testGroceryList.groceryListToString());
        assertEquals(parsedGroceryList.getGroceryListSize(), testGroceryList.getGroceryListSize());
    }

    @Test
    void testComparingParseAndNotSameGroceryList() throws NegativeInputException, EmptyStringException {
        GroceryList parsedGroceryList = parse(testGroceryListJSON);
        parsedGroceryList.printGroceryList();

        testGroceryList.addToGroceryList("Powdered Sugar", 1);
        testGroceryList.printGroceryList();

        assertNotEquals(parsedGroceryList.groceryListToString(), testGroceryList.groceryListToString());
        assertNotEquals(parsedGroceryList.getGroceryListSize(), testGroceryList.getGroceryListSize());
        for (int i = 0; i < 3; i++) {
            assertEquals(parsedGroceryList.getGroceryName(i), testGroceryList.getGroceryName(i));
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(parsedGroceryList.getGroceryQuantity(i), testGroceryList.getGroceryQuantity(i));
        }
    }
}
