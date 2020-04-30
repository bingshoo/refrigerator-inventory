package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.*;
import ca.ubc.cs.cpsc210.model.exceptions.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exceptions.NegativeInputException;
import ca.ubc.cs.cpsc210.ui.*;

import org.junit.jupiter.api.Test;

import static ca.ubc.cs.cpsc210.parsers.FridgeFreezerParser.parseFridge;
import static ca.ubc.cs.cpsc210.persistence.Jsonifier.fridgeToJson;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FridgeParserTest {
    private Fridge testFridge;
    private Item testFridgeItem1;
    private Item testFridgeItem2;
    private Item testFridgeItem3;
    private Item testFridgeItem4;
    private Item testFridgeItem5;

    @BeforeEach
    void setUp() throws EmptyStringException, NegativeInputException {
        testFridgeItem1 = new Item("Milk",
                new GregorianCalendar(2019, Calendar.JULY, 1),
                new GregorianCalendar(2019, Calendar.JUNE, 25),
                0.4, 3.50, false);
        testFridgeItem2 = new Item("Kraft Singles",
                new GregorianCalendar(2019, Calendar.AUGUST, 1),
                new GregorianCalendar(2019, Calendar.JUNE, 25),
                2.0, 5.00, false);
        testFridgeItem3 = new Item("Strawberry Jam",
                new GregorianCalendar(2019, Calendar.JULY, 31),
                new GregorianCalendar(2019, Calendar.JUNE, 25),
                0.4, 4.00, false);
        testFridgeItem4 = new Item("Apples",
                new GregorianCalendar(2019, Calendar.JULY, 10),
                new GregorianCalendar(2019, Calendar.JUNE, 25),
                2, 8, false);
        testFridgeItem5 = new Item("Ketchup",
                new GregorianCalendar(2019, Calendar.JULY, 1),
                new GregorianCalendar(2018, Calendar.JUNE, 25),
                0.6, 6.00, false);

        testFridge = new Fridge();
        testFridge.addItem(testFridgeItem1);
        testFridge.addItem(testFridgeItem2);
        testFridge.addItem(testFridgeItem3);
        testFridge.addItem(testFridgeItem4);
        testFridge.addItem(testFridgeItem5);
    }

    @Test
    void testFridgeParser() throws EmptyStringException, NegativeInputException {
        JSONObject testFridgeJson = fridgeToJson(testFridge);

        Fridge parsedFridge = parseFridge(testFridgeJson);
        assertEquals(parsedFridge.contentsToString(), testFridge.contentsToString());
        System.out.println(testFridgeJson.toString());
    }
}
